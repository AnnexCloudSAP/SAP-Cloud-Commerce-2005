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
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.annex.cloud.data.customer.AnnexCloudData;
import com.annexcloud.model.AnnexCloudModel;


/**
 * The Class ACAnnexSiteCredentialsPopulator.
 */
public class ACAnnexSiteCredentialsPopulator implements Populator<AnnexCloudModel, AnnexCloudData>
{

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
		customer.setTemplateId(annex.getTemplateId());
		customer.setSite(annex.getSiteId());
		customer.setLoginType(annex.getLoyaltyProgramType().getCode());
	}



}