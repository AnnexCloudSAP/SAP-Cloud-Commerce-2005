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
package com.annexcloud.returns.order;

import de.hybris.platform.processengine.action.AbstractSimpleDecisionAction;
import de.hybris.platform.returns.model.ReturnProcessModel;
import de.hybris.platform.returns.model.ReturnRequestModel;

import org.apache.log4j.Logger;

import com.annex.cloud.v3.api.response.user.ACReturnOrcerResponse;
import com.annexcloud.service.AnnexCloudLoyaltyService;


/**
 * The Class ACReturnOrderAction.
 */
public class ACReturnOrderAction extends AbstractSimpleDecisionAction<ReturnProcessModel>
{
	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ACReturnOrderAction.class);

	/** The Constant TRANSACTIONAL_STATUS. */
	private static final String TRANSACTIONAL_STATUS = "return";

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
	public void setAnnexCloudLoyaltyService(final AnnexCloudLoyaltyService annexCloudLoyaltyService)
	{
		this.annexCloudLoyaltyService = annexCloudLoyaltyService;
	}
}