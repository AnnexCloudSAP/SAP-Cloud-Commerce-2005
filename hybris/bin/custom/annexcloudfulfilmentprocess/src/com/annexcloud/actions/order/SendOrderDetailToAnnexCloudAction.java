/* Annex cloud Hybris Extension
 * Copyright (c) 2019 Annex cloud
 * All software and accompanying documents that you download from Annex Cloud are the copyrighted work of Annex Cloud and/or its suppliers.
 * Your use of the Software is governed by the terms of the software license agreement applicable to the Software ("License Agreement").
 * You are not authorized to install or use any Software unless you first agree to the License Agreement terms.
 * All rights, title, and interest to the Software not expressly granted are reserved.*/
package com.annexcloud.actions.order;


import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.orderprocessing.model.OrderProcessModel;
import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.annex.cloud.loyalty.jwt.ACCreditDebitPointsJwtRequest;
import com.annex.cloud.v3.api.response.ACResponse;
import com.annex.cloud.v3.api.response.user.ACOrderResponse;
import com.annex.cloud.v3.api.response.user.ACUserPointsResponse;
import com.annexcloud.model.AnnexCloudModel;
import com.annexcloud.service.AnnexCloudLoyaltyService;


/**
 * Action to send order detail to annex cloud.
 */
public class SendOrderDetailToAnnexCloudAction extends AbstractSimpleDecisionAction<OrderProcessModel>
{

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(SendOrderDetailToAnnexCloudAction.class);

	/** The Constant TRANSACTIONAL_STATUS. */
	private static final String TRANSACTIONAL_STATUS = "annexcloud.order.status";

	/** The Constant PRICE. */
	private static final String PRICE = "PRICE";

	/** The annex cloud loyalty service. */
	private AnnexCloudLoyaltyService annexCloudLoyaltyService;

	/**
	 * Execute action.
	 *
	 * @param process
	 *           the process
	 * @return the transition
	 */
	@Override
	public Transition executeAction(final OrderProcessModel process)
	{
		final OrderModel order = process.getOrder();
		if (order == null)
		{
			LOG.error("Missing the order, exiting the process");
			return Transition.NOK;
		}

		final ACOrderResponse response = (ACOrderResponse) getAnnexCloudLoyaltyService().orderAPI(order);
		Transition status = Transition.NOK;
		if (response.getRequestHasSucceeded())
		{
			status = Transition.OK;

			final AnnexCloudModel annexCloud = order.getStore().getAnnexCloud();

			if (PRICE.equals(annexCloud.getPointRedemptionType().getCode()) && order.getAnnexCloudUserCreditPoint() != null
					&& order.getAnnexCloudUserCreditPoint().getDeductAmount() > 0)
			{
				final ACUserPointsResponse userPointsDetails = annexCloudLoyaltyService.viewPointsDetailAPI(order.getUser().getUid(),
						annexCloud);
				if (userPointsDetails.getRequestHasSucceeded())
				{
					final Double usedPoints = order.getAnnexCloudUserCreditPoint().getDeductAmount()
							/ Double.parseDouble(userPointsDetails.getCreditsToCurrencyRatio());

					final ACCreditDebitPointsJwtRequest jwt = new ACCreditDebitPointsJwtRequest();
					jwt.setId(order.getUser().getUid());
					jwt.setDebit(usedPoints.toString());
					jwt.setActivity("DEBIT");
					jwt.setReason("Points used with order");
					jwt.setOrderId(order.getCode());

					final ACResponse acResponse = getAnnexCloudLoyaltyService().awardDeductCustomPointsAPI(jwt, annexCloud);
					if (!acResponse.getRequestHasSucceeded())
					{
						LOG.error(acResponse.getErrorMessage());
						status = Transition.NOK;
					}
				}
			}

		}
		else
		{
			LOG.error(response.getErrorMessage());
		}


		return status;
	}

	/**
	 * Gets the annex cloud loyalty service.
	 *
	 * @return the annex cloud loyalty service
	 */
	public AnnexCloudLoyaltyService getAnnexCloudLoyaltyService()
	{
		return annexCloudLoyaltyService;
	}

	/**
	 * Sets the annex cloud loyalty service.
	 *
	 * @param annexCloudLoyaltyService
	 *           the new annex cloud loyalty service
	 */
	@Required
	public void setAnnexCloudLoyaltyService(final AnnexCloudLoyaltyService annexCloudLoyaltyService)
	{
		this.annexCloudLoyaltyService = annexCloudLoyaltyService;
	}
}
