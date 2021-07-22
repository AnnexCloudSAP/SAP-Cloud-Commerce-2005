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

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.ResourceBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.ThirdPartyConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractSearchPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.store.services.BaseStoreService;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.annex.cloud.sitedetails.ACSiteDetailsOptions;
import com.annexcloud.constants.AnnexcloudaddonWebConstants;
import com.annexcloud.controllers.pages.forms.ACLoyaltyRegistrationForm;
import com.annexcloud.facade.AnnexCloudV3Facade;


/**
 * The Class ACLoyaltyPageController.
 */
@Controller("acLoyaltyPageController")
@RequestMapping("/my-account/loyalty-point")
public class ACLoyaltyPageController extends AbstractSearchPageController
{

	/** The Constant LOG. */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ACLoyaltyPageController.class);

	/** The Constant BREADCRUMBS_ATTR. */
	private static final String BREADCRUMBS_ATTR = "breadcrumbs";

	/** The Constant TEXT_ACCOUNT_LOYALTY_POINT. */
	private static final String TEXT_ACCOUNT_LOYALTY_POINT = "My Loyalty Points";

	/** The Constant LOYALTY_POINT_CMS_PAGE. */
	private static final String LOYALTY_POINT_CMS_PAGE = "loyalty-point";

	/** The account breadcrumb builder. */
	@Resource(name = "accountBreadcrumbBuilder")
	private ResourceBreadcrumbBuilder accountBreadcrumbBuilder;

	/** The base store service. */
	@Resource(name = "baseStoreService")
	private BaseStoreService baseStoreService;

	/** The common I 18 N service. */
	@Resource(name = "commonI18NService")
	private CommonI18NService commonI18NService;

	/** The annex cloud loyalty facade. */
	@Resource(name = "acV3LoyaltyFacade")
	private AnnexCloudV3Facade annexCloudLoyaltyFacade;

	/**
	 * Loyalty point.
	 *
	 * @param model
	 *           the model
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String loyaltyPoint(final Model model) throws CMSItemNotFoundException
	{
		if(!annexCloudLoyaltyFacade.getAnnexCloudUserDetails().getRequestHasSucceeded())
		{
			annexCloudLoyaltyFacade.createUserInAnnex();
		}
		fillAnnexRegistration(model);
		return getViewForPage(model);
	}

	/**
	 * Fill annex registration.
	 *
	 * @param model
	 *           the model
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	void fillAnnexRegistration(final Model model) throws CMSItemNotFoundException
	{
		storeCmsPageInModel(model, getContentPageForLabelOrId(LOYALTY_POINT_CMS_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(LOYALTY_POINT_CMS_PAGE));
		model.addAttribute(BREADCRUMBS_ATTR, accountBreadcrumbBuilder.getBreadcrumbs(TEXT_ACCOUNT_LOYALTY_POINT));
		model.addAttribute(ThirdPartyConstants.SeoRobots.META_ROBOTS, ThirdPartyConstants.SeoRobots.NOINDEX_NOFOLLOW);
		final List<ACSiteDetailsOptions> options = Arrays.asList(ACSiteDetailsOptions.ANNEX_USER_DETAILS);
		model.addAttribute("annexCloudData", annexCloudLoyaltyFacade.getAnnexCloudCredentialsWithOptions(options));
		model.addAttribute("annexLoyaltyRegistrationForm", new ACLoyaltyRegistrationForm());
	}


}
