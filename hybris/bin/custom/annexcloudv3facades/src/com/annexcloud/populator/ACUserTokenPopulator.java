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
package com.annexcloud.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.user.UserService;

import javax.annotation.Resource;

import com.annex.cloud.data.customer.AnnexCloudData;
import com.annex.cloud.v3.api.response.user.ACUserDetailsResponse;
import com.annexcloud.model.AnnexCloudModel;
import com.annexcloud.service.AnnexCloudLoyaltyService;


/**
 * The Class ACUserTokenPopulator.
 */
public class ACUserTokenPopulator implements Populator<AnnexCloudModel, AnnexCloudData>
{

	/** The user service. */
	@Resource(name = "userService")
	private UserService userService;

	/** The annex cloud loyalty service. */
	@Resource(name = "acV3LoyaltyService")
	private AnnexCloudLoyaltyService annexCloudLoyaltyService;

	/**
	 * Populate.
	 *
	 * @param annex
	 *           the annex
	 * @param customer
	 *           the customer
	 * @throws ConversionException
	 *            the conversion exception
	 */
	@Override
	public void populate(final AnnexCloudModel annex, final AnnexCloudData customer) throws ConversionException
	{
		final UserModel cust = userService.getCurrentUser();
		if (!userService.isAnonymousUser(cust))
		{
			customer.setUserId(cust.getUid());
			customer.setToken(annexCloudLoyaltyService.createToken(cust.getUid(), annex));
			final ACUserDetailsResponse userResponse = annexCloudLoyaltyService.userDetailsAPI(cust.getUid(), annex);
			customer.setActive(
					userResponse.getRequestHasSucceeded() && userResponse.getOptInStatus().equalsIgnoreCase("YES") ? true : false);
		}
		else
		{
			customer.setUserId("");
			customer.setToken("");
			customer.setActive(false);
		}
	}
}