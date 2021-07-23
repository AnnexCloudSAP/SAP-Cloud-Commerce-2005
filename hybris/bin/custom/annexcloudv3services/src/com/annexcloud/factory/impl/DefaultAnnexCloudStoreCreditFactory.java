/**

*********  ****     ***   ****     ***  ********* **       **
*********  *****    ***   *****    ***  *********  **     **
***   ***  *** **   ***   *** **   ***  ***         **   **
***   ***  *** **   ***   *** **   ***  *********    ** **
*********  ***  **  ***   ***  **  ***  *********     **
***   ***  ***   ** ***   ***   ** ***  ***         ** **
***   ***  ***    * ***   ***    * ***  *********  **   **
***   ***  ***     ****   ***     ****  ********* **     **


 ********   ***          *******    ***   ***   ********
*********   ***         *********   ***   ***   *********
***         ***         ***   ***   ***   ***   ***   ***
***         ***         ***   ***   ***   ***   ***   ***
***         ***         ***   ***   ***   ***   ***   ***
***         ***         ***   ***   ***   ***   ***   ***
*********   *********   *********   *********   *********
 ********   *********    *******     *******    ********


 Annex cloud Copyright (c) 2019
 All software and accompanying documents that you download from Annex Cloud
 are the copyrighted work of Annex Cloud and/or its suppliers. Your use of
 the Software is governed by the terms of the software license agreement
 applicable to the Software ("License Agreement"). You are not authorized to
 install or use any Software unless you first agree to the License Agreement
 terms. All rights, title, and interest to the Software not expressly granted
 are reserved.*/
package com.annexcloud.factory.impl;

import static com.annexcloud.constants.AnnexCloudV3Constants.STORE_CREDIT_PAYMENT_PROVIDER;

import de.hybris.platform.commerceservices.strategies.GenerateMerchantTransactionCodeStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;
import de.hybris.platform.servicelayer.model.ModelService;

import java.math.BigDecimal;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Required;

import com.annexcloud.factory.AnnexCloudStoreCreditFactory;


/**
 * A factory for creating DefaultAnnexCloudStoreCredit objects.
 */
public class DefaultAnnexCloudStoreCreditFactory implements AnnexCloudStoreCreditFactory
{

	/** The model service. */
	private ModelService modelService;

	/** The generate merchant transaction code strategy. */
	private GenerateMerchantTransactionCodeStrategy generateMerchantTransactionCodeStrategy;

	/**
	 * Creates a new DefaultAnnexCloudStoreCredit object.
	 *
	 * @param cartModel
	 *           the cart model
	 * @param amountToApply
	 *           the amount to apply
	 * @return the payment transaction model
	 */
	@Override
	public PaymentTransactionModel createPaymentTransaction(final CartModel cartModel, final BigDecimal amountToApply)
	{
		final PaymentTransactionModel paymentTransactionModel = getModelService().create(PaymentTransactionModel.class);
		paymentTransactionModel.setCode(getGenerateMerchantTransactionCodeStrategy().generateCode(cartModel));
		paymentTransactionModel.setCurrency(cartModel.getCurrency());
		paymentTransactionModel.setPlannedAmount(amountToApply);
		paymentTransactionModel.setPaymentProvider(STORE_CREDIT_PAYMENT_PROVIDER);
		paymentTransactionModel.setOrder(cartModel);
		return paymentTransactionModel;
	}

	/**
	 * Creates a new DefaultAnnexCloudStoreCredit object.
	 *
	 * @param cartModel
	 *           the cart model
	 * @param paymentTransactionModel
	 *           the payment transaction model
	 * @param transactionAmount
	 *           the transaction amount
	 * @return the payment transaction entry model
	 */

	@Override
	public PaymentTransactionEntryModel createPaymentTransactionEntry(final CartModel cartModel,
			final PaymentTransactionModel paymentTransactionModel, final BigDecimal transactionAmount,
			final PaymentTransactionType transactionType)
	{
		final PaymentTransactionEntryModel paymentTransactionEntryModel = getModelService()
				.create(PaymentTransactionEntryModel.class);

		paymentTransactionEntryModel.setCode(getNewPaymentTransactionEntryCode(paymentTransactionModel));
		paymentTransactionEntryModel.setAmount(transactionAmount);
		paymentTransactionEntryModel.setCurrency(cartModel.getCurrency());
		paymentTransactionEntryModel.setType(PaymentTransactionType.LOYALTY_POINT_CREDIT);
		paymentTransactionEntryModel.setPaymentTransaction(paymentTransactionModel);

		return paymentTransactionEntryModel;
	}





	/**
	 * Gets the new payment transaction entry code.
	 *
	 * @param transaction
	 *           the transaction
	 * @return the new payment transaction entry code
	 */
	private String getNewPaymentTransactionEntryCode(final PaymentTransactionModel transaction)
	{
		final int entryNumber;
		if (CollectionUtils.isEmpty(transaction.getEntries()))
		{
			entryNumber = 1;
		}
		else
		{
			entryNumber = transaction.getEntries().size() + 1;
		}
		return String.format("%s-%s-%s", transaction.getCode(), PaymentTransactionType.LOYALTY_POINT_CREDIT.getCode(), entryNumber);
	}

	/**
	 * Gets the model service.
	 *
	 * @return the model service
	 */
	public ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * Sets the model service.
	 *
	 * @param modelService
	 *           the new model service
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * Gets the generate merchant transaction code strategy.
	 *
	 * @return the generate merchant transaction code strategy
	 */
	public GenerateMerchantTransactionCodeStrategy getGenerateMerchantTransactionCodeStrategy()
	{
		return generateMerchantTransactionCodeStrategy;
	}

	/**
	 * Sets the generate merchant transaction code strategy.
	 *
	 * @param generateMerchantTransactionCodeStrategy
	 *           the new generate merchant transaction code strategy
	 */
	@Required
	public void setGenerateMerchantTransactionCodeStrategy(
			final GenerateMerchantTransactionCodeStrategy generateMerchantTransactionCodeStrategy)
	{
		this.generateMerchantTransactionCodeStrategy = generateMerchantTransactionCodeStrategy;
	}
}
