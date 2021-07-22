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
package com.annexcloud.controllers.pages.checkout.steps;


import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.CheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.checkout.steps.AbstractCheckoutStepController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.annex.cloud.data.reward.AnnexRewardData;
import com.annex.cloud.data.reward.AnnexUserPointsData;
import com.annex.cloud.v3.api.response.user.ACCustomActionResponse;
import com.annexcloud.controllers.AnnexcloudaddonControllerConstants;
import com.annexcloud.controllers.pages.forms.ACPointRedemptionForm;
import com.annexcloud.controllers.pages.forms.CouponRedemptionForm;
import com.annexcloud.controllers.pages.forms.validation.PointRedemptionValidator;
import com.annexcloud.enums.PointRedemptionType;
import com.annexcloud.facade.AnnexCloudV3Facade;


/**
 * The Class ACPointRedemptionCheckoutStepController.
 */
@Controller
@RequestMapping(value = "/checkout/multi/point-redemption")
public class ACPointRedemptionCheckoutStepController extends AbstractCheckoutStepController
{

	/** The Constant CART_DATA_ATTR. */
	private static final String CART_DATA_ATTR = "cartData";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ACPointRedemptionCheckoutStepController.class);

	/** The Constant POINT_REDEMPTION. */
	private static final String POINT_REDEMPTION = "point-redemption";

	/** The annex cloud loyalty facade. */
	@Resource(name = "acV3LoyaltyFacade")
	AnnexCloudV3Facade annexCloudLoyaltyFacade;

	/** The point redemption validator. */
	@Resource(name = "pointRedemptionValidator")
	PointRedemptionValidator pointRedemptionValidator;

	@Resource(name = "voucherFacade")
	VoucherFacade voucherFacade;

	/**
	 * Enter step.
	 *
	 * @param model
	 *           the model
	 * @param redirectAttributes
	 *           the redirect attributes
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	@Override
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@RequireHardLogIn
	public String enterStep(final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException
	{
		final AnnexUserPointsData userpoints = annexCloudLoyaltyFacade.getAnnexCloudUserPointsDetails();
		if (userpoints != null)
		{
			this.fillModel(model, userpoints);
			return AnnexcloudaddonControllerConstants.Views.Pages.MultiStepCheckout.AddPointRedemptionPage;
		}
		else
		{
			return getCheckoutStep().nextStep();
		}
	}

	/**
	 * Fill model.
	 *
	 * @param model
	 *           the model
	 * @param userpoints
	 *           the userpoints
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	void fillModel(final Model model, final AnnexUserPointsData userpoints) throws CMSItemNotFoundException
	{

		model.addAttribute("pointRedemptionType", userpoints.getPointsRedemtionType());

		if (PointRedemptionType.COUPON.equals(userpoints.getPointsRedemtionType()))
		{
			model.addAttribute("point", annexCloudLoyaltyFacade.getAnnexCloudUserPointsDetails());
			if (StringUtils.isBlank(annexCloudLoyaltyFacade.getAnnexAppliedCoupon()))
			{
				final List<AnnexRewardData> annexRewardList = annexCloudLoyaltyFacade.getAnnexCloudRewardList();
			  if (!annexRewardList.isEmpty())
				{
					model.addAttribute("annexcloudrewards", annexRewardList);
				}
			}
			else
			{
				model.addAttribute("acAppliedCoupon", annexCloudLoyaltyFacade.getAnnexAppliedCoupon());
			}
		}
		// Use the checkout PCI strategy for getting the URL for creating new subscriptions.
		setCheckoutStepLinksForModel(model, getCheckoutStep());
		final CartData cartData = getCheckoutFacade().getCheckoutCart();
		model.addAttribute(CART_DATA_ATTR, cartData);
		this.prepareDataForPage(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.point.redemption.breadcrumb"));
		model.addAttribute("metaRobots", "noindex,nofollow");
		// model.addAttribute("pointRedemptionForm", new ACPointRedemptionForm());
		model.addAttribute("couponRedemptionForm", new CouponRedemptionForm());
	}

	/**
	 * Adds the.
	 *
	 * @param model
	 *           the model
	 * @param pointRedemptionForm
	 *           the point redemption form
	 * @param bindingResult
	 *           the binding result
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */


	@RequireHardLogIn
	@RequestMapping(value =
	{ "/addCoupon" }, method = RequestMethod.POST)
	public String getProductPurchaseCoupons(final Model model, @Valid
	final CouponRedemptionForm couponRedemptionForm, final BindingResult bindingResult) throws CMSItemNotFoundException
	{
		setCheckoutStepLinksForModel(model, getCheckoutStep());
		if (couponRedemptionForm.getRewardId().equalsIgnoreCase("0"))
		{
			return getCheckoutStep().nextStep();
		}
		final ACCustomActionResponse usercoupons = annexCloudLoyaltyFacade.redeemCoupons(couponRedemptionForm.getRewardId());
		return getCheckoutStep().nextStep();

	}

	@RequireHardLogIn
	@RequestMapping(value =
	{ "/removeCoupon" }, method = RequestMethod.POST)
	public String removeProductPurchaseCoupons(final Model model, @Valid
	final CouponRedemptionForm couponRedemptionForm, final BindingResult bindingResult) throws CMSItemNotFoundException
	{
		try
		{
			if (StringUtils.isNoneBlank(couponRedemptionForm.getCoupon()))
			{
				voucherFacade.releaseVoucher(couponRedemptionForm.getCoupon());
			}
		}
		catch (final VoucherOperationException e)
		{ // XXX Auto-generated catch block e.printStackTrace(); } final
			LOGGER.error(e.getMessage());
		}
		final AnnexUserPointsData userpoints = annexCloudLoyaltyFacade.getAnnexCloudUserPointsDetails();
		this.fillModel(model, userpoints);
		setCheckoutStepLinksForModel(model, getCheckoutStep());
		return getCheckoutStep().currentStep();
	}


	/**
	 * Gets the checkout step.
	 *
	 * @return the checkout step
	 */
	protected CheckoutStep getCheckoutStep()
	{
		return getCheckoutStep(POINT_REDEMPTION);
	}

	/**
	 * Back.
	 *
	 * @param redirectAttributes
	 *           the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	public String back(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().previousStep();
	}

	/**
	 * Next.
	 *
	 * @param redirectAttributes
	 *           the redirect attributes
	 * @return the string
	 */
	@RequestMapping(value = "/next", method = RequestMethod.GET)
	@RequireHardLogIn
	@Override
	public String next(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().nextStep();
	}

	/**
	 * Gets the annex cloud loyalty facade.
	 *
	 * @return the annex cloud loyalty facade
	 */
	public AnnexCloudV3Facade getAnnexCloudLoyaltyFacade()
	{
		return annexCloudLoyaltyFacade;
	}

	/**
	 * Sets the annex cloud loyalty facade.
	 *
	 * @param annexCloudLoyaltyFacade
	 *           the new annex cloud loyalty facade
	 */
	public void setAnnexCloudLoyaltyFacade(final AnnexCloudV3Facade annexCloudLoyaltyFacade)
	{
		this.annexCloudLoyaltyFacade = annexCloudLoyaltyFacade;
	}

	/**
	 * Gets the point redemption validator.
	 *
	 * @return the point redemption validator
	 */
	public PointRedemptionValidator getPointRedemptionValidator()
	{
		return pointRedemptionValidator;
	}

	/**
	 * Sets the point redemption validator.
	 *
	 * @param pointRedemptionValidator
	 *           the new point redemption validator
	 */
	public void setPointRedemptionValidator(final PointRedemptionValidator pointRedemptionValidator)
	{
		this.pointRedemptionValidator = pointRedemptionValidator;
	}
}
