package com.annexcloud.actions.order;

import de.hybris.platform.basecommerce.enums.ReturnStatus;
import de.hybris.platform.payment.AdapterException;
import de.hybris.platform.payment.PaymentService;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.returns.model.ReturnProcessModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.task.RetryLaterException;
import de.hybris.platform.warehousing.returns.service.RefundAmountCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TestCaptureRefundAction extends AbstractSimpleDecisionAction<ReturnProcessModel>
{
    private static final Logger LOG = LoggerFactory.getLogger(TestCaptureRefundAction.class);

    private PaymentService paymentService;
    private RefundAmountCalculationService refundAmountCalculationService;

    @Override
    public Transition executeAction(final ReturnProcessModel process) throws RetryLaterException, Exception
    {
        LOG.debug("Process: {} in step {}", process.getCode(), getClass().getSimpleName());

        final ReturnRequestModel returnRequest = process.getReturnRequest();
        final Optional<PaymentTransactionModel> transactions = returnRequest.getOrder().getPaymentTransactions().stream().filter(transaction->transaction.getPaymentProvider().equals("Mockup")).findAny();

        if (transactions.isEmpty())
        {
            LOG.info("Unable to refund for ReturnRequest {}, no PaymentTransactions found", returnRequest.getCode());
            setReturnRequestStatus(returnRequest, ReturnStatus.PAYMENT_REVERSAL_FAILED);
            return Transition.NOK;
        }
        //This assumes that the Order only has one PaymentTransaction
        final PaymentTransactionModel transaction = transactions.get();

        Transition result;
        if (transaction.getPaymentProvider() != null)
        {
            final BigDecimal customRefundAmount = refundAmountCalculationService.getCustomRefundAmount(returnRequest);
            BigDecimal amountToRefund = null;

            if (customRefundAmount != null && customRefundAmount.compareTo(BigDecimal.ZERO) > 0)
            {
                amountToRefund = customRefundAmount;
            }
            else
            {
                amountToRefund = refundAmountCalculationService.getOriginalRefundAmount(returnRequest);
            }

            try
            {
                getPaymentService().refundFollowOn(transaction, amountToRefund);
                setReturnRequestStatus(returnRequest, ReturnStatus.PAYMENT_REVERSED);
                result = Transition.OK;
            }
            catch (final AdapterException e)  //NOSONAR
            {
                LOG.info("Unable to refund for ReturnRequest {} , exception occurred {}", returnRequest.getCode(), e.getMessage());
                setReturnRequestStatus(returnRequest, ReturnStatus.PAYMENT_REVERSAL_FAILED);
                result = Transition.NOK;
            }
        }
        else
        {
            LOG.info("Payment Provider not available in the Payment Transaction.");
            result = Transition.OK;
        }

        return result;
    }

    /**
     * Update the return status for all return entries in {@link ReturnRequestModel}
     *
     * @param returnRequest
     *           - the return request
     * @param status
     *           - the return status
     */
    protected void setReturnRequestStatus(final ReturnRequestModel returnRequest, final ReturnStatus status)
    {
        returnRequest.setStatus(status);
        returnRequest.getReturnEntries().stream().forEach(entry -> {
            entry.setStatus(status);
            getModelService().save(entry);
        });
        getModelService().save(returnRequest);
    }

    protected PaymentService getPaymentService()
    {
        return paymentService;
    }

    @Required
    public void setPaymentService(final PaymentService paymentService)
    {
        this.paymentService = paymentService;
    }

    protected RefundAmountCalculationService getRefundAmountCalculationService()
    {
        return refundAmountCalculationService;
    }

    @Required
    public void setRefundAmountCalculationService(RefundAmountCalculationService refundAmountCalculationService)
    {
        this.refundAmountCalculationService = refundAmountCalculationService;
    }
}