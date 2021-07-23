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
package com.annexcloud.facade.impl;

import com.annexcloud.enums.LoyaltyProgram;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.voucher.VoucherFacade;
import de.hybris.platform.converters.ConfigurablePopulator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.user.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.annex.cloud.data.customer.AnnexCloudData;
import com.annex.cloud.data.reward.AnnexRewardData;
import com.annex.cloud.data.reward.AnnexUserPointsData;
import com.annex.cloud.loyalty.jwt.ACCartCalculationJwtRequest;
import com.annex.cloud.loyalty.jwt.ACCreditDebitPointsJwtRequest;
import com.annex.cloud.loyalty.jwt.ACProductDetailsJwtRequest;
import com.annex.cloud.loyalty.jwt.ACProductJwtRequest;
import com.annex.cloud.loyalty.jwt.ACProductWrapperJwtRequest;
import com.annex.cloud.product.data.AnnexCloudProductDetailsData;
import com.annex.cloud.sitedetails.ACSiteDetailsOptions;
import com.annex.cloud.v3.api.response.ACResponse;
import com.annex.cloud.v3.api.response.user.ACCartCalculationResponse;
import com.annex.cloud.v3.api.response.user.ACCustomActionResponse;
import com.annex.cloud.v3.api.response.user.ACProductPointWrapperResponse;
import com.annex.cloud.v3.api.response.user.ACRewardListResponse;
import com.annex.cloud.v3.api.response.user.ACUserDetailsResponse;
import com.annex.cloud.v3.api.response.user.ACUserPointsResponse;
import com.annexcloud.coupon.ACCouponService;
import com.annexcloud.enums.CreditDebitType;
import com.annexcloud.facade.AnnexCloudV3Facade;
import com.annexcloud.model.AnnexCloudModel;
import com.annexcloud.service.AnnexCloudLoyaltyService;


// TODO: Auto-generated Javadoc
/**
 * The Class DefaultAnnexCloudV3Facade.
 */
public class DefaultAnnexCloudV3Facade implements AnnexCloudV3Facade
{

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(DefaultAnnexCloudV3Facade.class);

	/** The annex cloud loyalty service. */
	private AnnexCloudLoyaltyService annexCloudLoyaltyService;

	/** The user points data converter. */
	private Converter<ACUserPointsResponse, AnnexUserPointsData> userPointsDataConverter;

	/** The a C annex site credentials converter. */
	private Converter<AnnexCloudModel, AnnexCloudData> aCAnnexSiteCredentialsConverter;

	/** The ac site details populator. */
	private ConfigurablePopulator<AnnexCloudModel, AnnexCloudData, ACSiteDetailsOptions> acSiteDetailsPopulator;

	/** The user service. */
	private UserService userService;

	/** The coupon service. */
	private ACCouponService couponService;

	/** The voucher facade. */
	private VoucherFacade voucherFacade;

	/** The cart facade. */
	private CartFacade cartFacade;

	/**
	 * Gets the annex cloud credentials.
	 *
	 * @return the annex cloud credentials
	 */
	@Override
	public AnnexCloudData getAnnexCloudCredentials()
	{
		final AnnexCloudData acData = getaCAnnexSiteCredentialsConverter()
				.convert(annexCloudLoyaltyService.getAnnexCloudCredential());
		return acData;
	}

	/**
	 * Gets the annex cloud credentials with options.
	 *
	 * @param options
	 *           the options
	 * @return the annex cloud credentials with options
	 */
	@Override
	public AnnexCloudData getAnnexCloudCredentialsWithOptions(final List<ACSiteDetailsOptions> options)
	{
		final AnnexCloudModel annexModel = annexCloudLoyaltyService.getAnnexCloudCredential();
		final AnnexCloudData acData = getaCAnnexSiteCredentialsConverter().convert(annexModel);
		acSiteDetailsPopulator.populate(annexModel, acData, options);
		return acData;
	}

	/**
	 * Save credit point in cart.
	 *
	 * @param rewardId
	 *           the reward id
	 * @param deductAmount
	 *           the deduct amount
	 */
	@Override
	public void saveCreditPointInCart(final String rewardId, final Double deductAmount)
	{
		getAnnexCloudLoyaltyService().saveCreditPointInCart(rewardId, deductAmount);
	}

	/**
	 * Gets the product purchase points.
	 *
	 * @param productId
	 *           the product id
	 * @return the product purchase points
	 */
	@Override
	public AnnexCloudProductDetailsData getProductPurchasePoints(final ProductData productId)
	{
		final AnnexCloudProductDetailsData annexCloudProductDetailsData = new AnnexCloudProductDetailsData();
		final UserModel user = getUserService().getCurrentUser();
		if (!getUserService().isAnonymousUser(user))
		{
			final AnnexCloudModel annexSiteDetails = annexCloudLoyaltyService.getAnnexCloudCredential();
			final ACUserDetailsResponse userActivity = annexCloudLoyaltyService.userDetailsAPI(user.getUid(), annexSiteDetails);
			//check if user is ACTIVE
			if (userActivity.getRequestHasSucceeded() && userActivity.getOptInStatus().equalsIgnoreCase("YES"))
			{
				final ACProductPointWrapperResponse acProductResponse = annexCloudLoyaltyService
						.productInformationAPI(getBearerToken(productId, user.getUid()), annexSiteDetails);
				if (acProductResponse.getRequestHasSucceeded())
				{
					// Get credit to point ration for user
					final ACUserPointsResponse userPoints = annexCloudLoyaltyService.viewPointsDetailAPI(user.getUid(),
							annexSiteDetails);
					if (userPoints.getRequestHasSucceeded())
					{
						final Double productPrice = Double.parseDouble(acProductResponse.getData().get(0).getUnitPrice());
						final double productCost = new BigDecimal(
								Math.ceil(productPrice) / Double.parseDouble(userPoints.getCreditsToCurrencyRatio()))
										.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
						annexCloudProductDetailsData.setCost(productCost);
						annexCloudProductDetailsData
								.setTierMultiplier(acProductResponse.getData().get(0).getPointsRatio().toString());
					}
					annexCloudProductDetailsData
							.setEarnPoints(Double.parseDouble(acProductResponse.getData().get(0).getPoints().toString()));
				}
				else
				{
					annexCloudProductDetailsData.setEarnPoints(0.0);
					LOG.debug("error_code :" + acProductResponse.getErrorCode() + "   description: "
							+ acProductResponse.getErrorMessage());
				}
			}
		}
		return annexCloudProductDetailsData;
	}

	/**
	 * Gets the cart calculation points.
	 *
	 * @return the cart calculation points
	 */
	@Override
	public ACCartCalculationResponse getCartCalculationPoints()
	{
		final ACCartCalculationResponse cartErrorResponse = new ACCartCalculationResponse();
		cartErrorResponse.setRequestHasSucceeded(false);
		cartErrorResponse.setCartPoints(0.0);
		final AnnexCloudModel annex = annexCloudLoyaltyService.getAnnexCloudCredential();


		final UserModel user = getUserService().getCurrentUser();
		final CartData cart = getCartFacade().getSessionCart();
		final ACUserDetailsResponse userActivity = annexCloudLoyaltyService.userDetailsAPI(user.getUid(), annex);
		if (!getUserService().isAnonymousUser(user) && userActivity.getRequestHasSucceeded()
				&& userActivity.getOptInStatus().equalsIgnoreCase("YES"))
		{
			final ACCartCalculationJwtRequest jwtRequest = new ACCartCalculationJwtRequest();
			final List<ACProductDetailsJwtRequest> listProducts = new ArrayList();
			for (final OrderEntryData entry : cart.getEntries())
			{
				final ACProductDetailsJwtRequest productJwtRequest = new ACProductDetailsJwtRequest();
				productJwtRequest.setId(entry.getProduct().getCode());
				productJwtRequest.setQuantity(entry.getQuantity().toString());
				productJwtRequest.setUnitPrice(entry.getBasePrice().getValue().toString());
				listProducts.add(productJwtRequest);
			}
			jwtRequest.setProductDetail(listProducts);
			jwtRequest.setUserId(user.getUid());
			final ACCartCalculationResponse cartResponse = annexCloudLoyaltyService.cartCalculationAPI(jwtRequest, annex);
			if (cartResponse.getRequestHasSucceeded())
			{
				return cartResponse;
			}
			else
			{
				return cartErrorResponse;
			}
		}
		else
		{
			return cartErrorResponse;
		}
	}


	/**
	 * Gets the bearer token.
	 *
	 * @param product
	 *           the product
	 * @param user
	 *           the user
	 * @return the bearer token
	 */
	private ACProductWrapperJwtRequest getBearerToken(final ProductData product, final String user)
	{
		//set product info
		final ACProductJwtRequest productJwtRequest = new ACProductJwtRequest();
		productJwtRequest.setId(product.getCode());
		productJwtRequest.setQuantity("1");
		productJwtRequest.setUnitPrice(product.getPrice().getValue().toString());
		productJwtRequest.setAutoDelivery("NO");

		//add productInfo model to list
		final List<ACProductJwtRequest> productMap = new ArrayList();
		productMap.add(productJwtRequest);

		//set list to wrapper class with user
		final ACProductWrapperJwtRequest productWrapper = new ACProductWrapperJwtRequest();
		productWrapper.setProductDetail(productMap);
		productWrapper.setId(user);
		return productWrapper;
	}

	/**
	 * Gets the annex cloud reward list.
	 *
	 * @return the annex cloud reward list
	 */
	@Override
	public List<AnnexRewardData> getAnnexCloudRewardList()
	{
		final UserModel customer = getUserService().getCurrentUser();
		final ACRewardListResponse annexCloudRewardListResponse = getAnnexCloudLoyaltyService()
				.getEligibleRewardListAPI(customer.getUid(), getAnnexCloudLoyaltyService().getAnnexCloudCredential());
		// Response Body Data

		if (annexCloudRewardListResponse.getRequestHasSucceeded())
		{
			final CartData cart = cartFacade.getSessionCart();
			return annexCloudRewardListResponse.getRewardDetail().stream()
					.filter(reward -> reward.getEligible().equalsIgnoreCase("YES")).map(s -> {
						final AnnexRewardData a = new AnnexRewardData();
						a.setRewardId(s.getRewardId());
						a.setRewardName(s.getDisplayText());
						a.setDeductAmount(Double.parseDouble(s.getCreditRequired()));
						return a;
					}).collect(Collectors.toList());
		}
		else
		{
			LOG.debug("error_code :" + annexCloudRewardListResponse.getErrorCode() + "   description: "
					+ annexCloudRewardListResponse.getErrorMessage());
			return Collections.emptyList();
		}
	}


	/**
	 * Gets the annex cloud user points details.
	 *
	 * @return the annex cloud user points details
	 */
	@Override
	public AnnexUserPointsData getAnnexCloudUserPointsDetails()
	{
		final UserModel customer = getUserService().getCurrentUser();
		final AnnexCloudModel annex = annexCloudLoyaltyService.getAnnexCloudCredential();
		final ACUserPointsResponse userPointsResponse = annexCloudLoyaltyService.viewPointsDetailAPI(customer.getUid(), annex);
		if (userPointsResponse.getRequestHasSucceeded())
		{
			final AnnexUserPointsData userDetails = getUserPointsDataConverter().convert(userPointsResponse);
			userDetails.setPointsRedemtionType(annex.getPointRedemptionType());
			return userDetails;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Gets the annex cloud user active status.
	 *
	 * @return the annex cloud user active status
	 */
	@Override
	public boolean getAnnexCloudUserActiveStatus()
	{
		final UserModel user = userService.getCurrentUser();
		if (!userService.isAnonymousUser(user))
		{
			final ACUserDetailsResponse userResponse = getAnnexCloudLoyaltyService().userDetailsAPI(user.getUid(),
					annexCloudLoyaltyService.getAnnexCloudCredential());
			return userResponse.getRequestHasSucceeded() && userResponse.getOptInStatus().equalsIgnoreCase("YES") ? true : false;
		}
		else
		{
			return false;
		}
	}

	@Override
	public ACUserDetailsResponse getAnnexCloudUserDetails()
	{
		final UserModel user = userService.getCurrentUser();
		return getAnnexCloudLoyaltyService().userDetailsAPI(user.getUid(),annexCloudLoyaltyService.getAnnexCloudCredential());
	}


	/**
	 * Creates the user in annex.
	 *
	 */
	@Override
	public void createUserInAnnex()
	{
		final UserModel user = userService.getCurrentUser();
 		final AnnexCloudModel annex = annexCloudLoyaltyService.getAnnexCloudCredential();
		getAnnexCloudLoyaltyService().createUserAPI((CustomerModel)user, annex);
		if (annex.getLoyaltyProgramType().equals(LoyaltyProgram.IMPLICIT))
		getAnnexCloudLoyaltyService().optInUserAPI(user.getUid(), annex);
	}
	/**
	 * Redeem coupons.
	 *
	 * @param rewardId
	 *           the reward id
	 * @return the AC custom action response
	 */
	public ACCustomActionResponse redeemCoupons(final String rewardId)
	{
		final UserModel customer = getUserService().getCurrentUser();
		final AnnexCloudModel annex = annexCloudLoyaltyService.getAnnexCloudCredential();
		final ACCreditDebitPointsJwtRequest userCouponRequest = new ACCreditDebitPointsJwtRequest();
		userCouponRequest.setId(customer.getUid());
		userCouponRequest.setRewardId(rewardId);
		userCouponRequest.setActivity("DEBIT");
		userCouponRequest.setReason("claim");
		userCouponRequest.setSource("web");
		LOG.debug("getUid:" + userCouponRequest.getId());
		LOG.debug("rewardId:" + userCouponRequest.getRewardId());
		LOG.debug("Activity:" + userCouponRequest.getActivity());
		LOG.debug("Reason:" + userCouponRequest.getReason());
		final ACCustomActionResponse customActionResponse = (ACCustomActionResponse) annexCloudLoyaltyService
				.awardDeductCustomPointsAPI(userCouponRequest, annex);
		try
		{
			if (customActionResponse.getRequestHasSucceeded())
			{
				final String coupon = customActionResponse.getRewardCode();
				voucherFacade.applyVoucher(coupon);
				if (!getCouponService().saveAnnexCoupon(coupon))
				{
					LOG.error("failed to save coupon in AnnexCoupon for user " + customer.getUid() + " coupon code : " + coupon);
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return customActionResponse;
	}

	/**
	 * Gets the annex applied coupon.
	 *
	 * @return the annex applied coupon
	 */
	public String getAnnexAppliedCoupon()
	{
		return couponService.getAnnexAppliedCoupon();
	}



	/**
	 * Gets the user points data converter.
	 *
	 * @return the user points data converter
	 */
	public Converter<ACUserPointsResponse, AnnexUserPointsData> getUserPointsDataConverter()
	{
		return userPointsDataConverter;
	}

	/**
	 * Sets the user points data converter.
	 *
	 * @param userPointsDataConverter
	 *           the user points data converter
	 */
	@Required
	public void setUserPointsDataConverter(final Converter<ACUserPointsResponse, AnnexUserPointsData> userPointsDataConverter)
	{
		this.userPointsDataConverter = userPointsDataConverter;
	}

	/**
	 * Gets the user service.
	 *
	 * @return the user service
	 */
	public UserService getUserService()
	{
		return userService;
	}

	/**
	 * Sets the user service.
	 *
	 * @param userService
	 *           the new user service
	 */
	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * Gets the a C annex site credentials converter.
	 *
	 * @return the a C annex site credentials converter
	 */
	public Converter<AnnexCloudModel, AnnexCloudData> getaCAnnexSiteCredentialsConverter()
	{
		return aCAnnexSiteCredentialsConverter;
	}

	/**
	 * Seta C annex site credentials converter.
	 *
	 * @param aCAnnexSiteCredentialsConverter
	 *           the a C annex site credentials converter
	 */
	public void setaCAnnexSiteCredentialsConverter(
			final Converter<AnnexCloudModel, AnnexCloudData> aCAnnexSiteCredentialsConverter)
	{
		this.aCAnnexSiteCredentialsConverter = aCAnnexSiteCredentialsConverter;
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


	/**
	 * Gets the ac site details populator.
	 *
	 * @return the ac site details populator
	 */
	public ConfigurablePopulator<AnnexCloudModel, AnnexCloudData, ACSiteDetailsOptions> getAcSiteDetailsPopulator()
	{
		return acSiteDetailsPopulator;
	}

	/**
	 * Sets the ac site details populator.
	 *
	 * @param acSiteDetailsPopulator
	 *           the ac site details populator
	 */
	public void setAcSiteDetailsPopulator(
			final ConfigurablePopulator<AnnexCloudModel, AnnexCloudData, ACSiteDetailsOptions> acSiteDetailsPopulator)
	{
		this.acSiteDetailsPopulator = acSiteDetailsPopulator;
	}

	/**
	 * Gets the coupon service.
	 *
	 * @return the coupon service
	 */
	public ACCouponService getCouponService()
	{
		return couponService;
	}

	/**
	 * Sets the coupon service.
	 *
	 * @param couponService
	 *           the new coupon service
	 */
	public void setCouponService(final ACCouponService couponService)
	{
		this.couponService = couponService;
	}

	/**
	 * Gets the voucher facade.
	 *
	 * @return the voucher facade
	 */
	public VoucherFacade getVoucherFacade()
	{
		return voucherFacade;
	}

	/**
	 * Sets the voucher facade.
	 *
	 * @param voucherFacade
	 *           the new voucher facade
	 */
	public void setVoucherFacade(final VoucherFacade voucherFacade)
	{
		this.voucherFacade = voucherFacade;
	}

	/**
	 * Gets the cart facade.
	 *
	 * @return the cart facade
	 */
	public CartFacade getCartFacade()
	{
		return cartFacade;
	}

	/**
	 * Sets the cart facade.
	 *
	 * @param cartFacade
	 *           the new cart facade
	 */
	public void setCartFacade(final CartFacade cartFacade)
	{
		this.cartFacade = cartFacade;
	}



}
