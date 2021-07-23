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
package com.annexcloud.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.annex.cloud.data.customer.AnnexCloudData;
import com.annexcloud.facade.AnnexCloudV3Facade;


/**
 * The Class ACGalleryPageController.
 */
@RequestMapping("/picture")
public class ACGalleryPageController extends AbstractSearchPageController
{

	/** The Constant GALLERY_PAGE. */
	private static final String GALLERY_PAGE = "galleryPage";

	/** The annex cloud loyalty facade. */
	@Resource(name = "acV3LoyaltyFacade")
	private AnnexCloudV3Facade annexCloudLoyaltyFacade;

	/**
	 * Display gallery page.
	 *
	 * @param model
	 *           the model
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	@RequestMapping(value = "/galleryPage", method = RequestMethod.GET)
	public String displayGalleryPage(final Model model) throws CMSItemNotFoundException
	{

		final AnnexCloudData annexCloudData = annexCloudLoyaltyFacade.getAnnexCloudCredentials();

		storeCmsPageInModel(model, getContentPageForLabelOrId(GALLERY_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(GALLERY_PAGE));
		model.addAttribute("siteId", annexCloudData.getSite());
		return getViewForPage(model);
	}

}
