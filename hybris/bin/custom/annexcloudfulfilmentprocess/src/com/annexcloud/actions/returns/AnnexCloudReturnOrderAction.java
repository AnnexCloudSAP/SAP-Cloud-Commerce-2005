/* Annex cloud Hybris Extension
 * Copyright (c) 2019 Annex cloud
 * All software and accompanying documents that you download from Annex Cloud are the copyrighted work of Annex Cloud and/or its suppliers.
 * Your use of the Software is governed by the terms of the software license agreement applicable to the Software ("License Agreement").
 * You are not authorized to install or use any Software unless you first agree to the License Agreement terms.
 * All rights, title, and interest to the Software not expressly granted are reserved.*/
package com.annexcloud.actions.returns;

import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.returns.model.ReturnProcessModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import org.apache.log4j.Logger;

import com.annex.cloud.v3.api.response.user.ACReturnOrcerResponse;
import com.annexcloud.service.AnnexCloudLoyaltyService;


/**
 * The type Annex cloud return order action.
 */
public class AnnexCloudReturnOrderAction extends AbstractSimpleDecisionAction<ReturnProcessModel>
{

	private static final Logger LOG = Logger.getLogger(AnnexCloudReturnOrderAction.class);

	private static final String TRANSACTIONAL_STATUS = "return";

	private AnnexCloudLoyaltyService annexCloudLoyaltyService;

	@Override
	public Transition executeAction(final ReturnProcessModel process)
	{

		LOG.info("Process: " + process.getCode() + " in step " + getClass().getSimpleName());

		final ReturnRequestModel returnRequest = process.getReturnRequest();
		if (returnRequest == null || returnRequest.getOrder() == null)
		{
			LOG.error("Missing the order and Return Request, exiting the process");
			return Transition.NOK;
		}

		final ACReturnOrcerResponse acResponse1 = annexCloudLoyaltyService.returnOrderAPI(returnRequest);
		if (acResponse1.getRequestHasSucceeded())
		{
			LOG.info(" order id " + acResponse1.getOrderId() + " ,points removed " + acResponse1.getPointsRemoved());
			return Transition.OK;
		}
		else
		{
			return Transition.NOK;
		}


	}

	public AnnexCloudLoyaltyService getAnnexCloudLoyaltyService()
	{
		return annexCloudLoyaltyService;
	}

	public void setAnnexCloudLoyaltyService(final AnnexCloudLoyaltyService annexCloudLoyaltyService)
	{
		this.annexCloudLoyaltyService = annexCloudLoyaltyService;
	}
}
