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
package com.annexcloud.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.commerceservices.event.RegisterEvent;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.springframework.beans.factory.annotation.Required;

import com.annexcloud.enums.LoyaltyProgram;
import com.annexcloud.model.AnnexCloudModel;
import com.annexcloud.service.AnnexCloudLoyaltyService;


/**
 * The listener interface for receiving ACRegistrationEvent events. The class that is interested in processing a
 * ACRegistrationEvent event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addACRegistrationEventListener<code> method. When the ACRegistrationEvent event
 * occurs, that object's appropriate method is invoked.
 *
 * @see ACRegistrationEventEvent
 */
public class ACRegistrationEventListener extends AbstractAcceleratorSiteEventListener<RegisterEvent>
{

	/** The model service. */
	private ModelService modelService;

	/** The business process service. */
	private BusinessProcessService businessProcessService;

	/** The annex cloud loyalty service. */
	private AnnexCloudLoyaltyService annexCloudLoyaltyService;

	/**
	 * On site event.
	 *
	 * @param registerEvent
	 *           the register event
	 */
	@Override
	protected void onSiteEvent(final RegisterEvent registerEvent)
	{
		final AnnexCloudModel annex = annexCloudLoyaltyService.getAnnexCloudCredential();

		if(!annex.getAcPkType().getCode().equals("GYUID"))
		{
			getAnnexCloudLoyaltyService().createUserAPI(registerEvent.getCustomer(), annex);

			if (annex.getLoyaltyProgramType().equals(LoyaltyProgram.IMPLICIT))
			{
				getAnnexCloudLoyaltyService().optInUserAPI(registerEvent.getCustomer().getUid(), annex);
			}
		}
		/*getAnnexCloudLoyaltyService().createUserAPI(registerEvent.getCustomer(), annex);

		if (annex.getLoyaltyProgramType().equals(LoyaltyProgram.IMPLICIT))
		{
			getAnnexCloudLoyaltyService().optInUserAPI(registerEvent.getCustomer().getUid(), annex);
		}*/
	}

	/**
	 * Gets the site channel for event.
	 *
	 * @param event
	 *           the event
	 * @return the site channel for event
	 */
	@Override
	protected SiteChannel getSiteChannelForEvent(final RegisterEvent event)
	{
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return site.getChannel();
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
	 * Gets the business process service.
	 *
	 * @return the business process service
	 */
	public BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	/**
	 * Sets the business process service.
	 *
	 * @param businessProcessService
	 *           the new business process service
	 */
	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
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
