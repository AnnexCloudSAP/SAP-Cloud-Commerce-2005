package com.annexcloud.controllers.cms;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.annex.cloud.data.customer.AnnexCloudData;
import com.annexcloud.facade.AnnexCloudV3Facade;
import com.annexcloud.model.components.AnnexCloudImageSliderHomePageComponentModel;


/**
 * Controller for image slider home page sets siteId
 */
@Scope("tenant")
@RequestMapping("/view/AnnexCloudImageSliderHomePageComponentController")
public class AnnexCloudImageSliderHomePageComponentController
		extends AbstractCMSComponentController<AnnexCloudImageSliderHomePageComponentModel>
{

	private static final String ADDON_PREFIX = "addon:/annexcloudaddon/";

	@Resource(name = "acV3LoyaltyFacade")
	private AnnexCloudV3Facade annexCloudLoyaltyFacade;

	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final AnnexCloudImageSliderHomePageComponentModel component)
	{
		final AnnexCloudData annexCloudData = annexCloudLoyaltyFacade.getAnnexCloudCredentials();
		model.addAttribute("siteId", annexCloudData.getSite());
	}

	@Override
	protected String getView(final AnnexCloudImageSliderHomePageComponentModel component)
	{
		return ADDON_PREFIX + "/cms/annexCloudImageSliderHomePageComponent";
	}
}
