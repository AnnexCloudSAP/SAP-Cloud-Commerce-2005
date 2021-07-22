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
package com.annexcloud.service;

import com.annex.cloud.loyalty.ACRequestDetails;
import com.annex.cloud.loyalty.jwt.*;
import com.annex.cloud.loyalty.jwt.order.ACOrderJwtRequest;
import com.annex.cloud.loyalty.jwt.order.ACOrderProductJwtRequest;
import com.annex.cloud.v3.api.response.ACResponse;
import com.annex.cloud.v3.api.response.segment.ACCreateSegmentResponse;
import com.annex.cloud.v3.api.response.segment.ACUpdateSegmentResponse;
import com.annex.cloud.v3.api.response.user.*;
import com.annex.cloud.v3.api.response.user.action.ACAllActionDetailResponse;
import com.annex.cloud.v3.api.response.user.action.ACUserActionDetailsResponse;
import com.annex.cloud.v3.api.response.user.activity.ACUserActivityResponse;
import com.annex.cloud.v3.api.response.user.campaign.ACCampaignDetailsResponse;
import com.annex.cloud.v3.api.response.user.campaign.ACStoreDetailsResponse;
import com.annex.cloud.v3.api.response.user.segment.ACSegmentDetailsWrapperResponse;
import com.annex.cloud.v3.api.response.user.tier.ACTierInformationResponse;
import com.annexcloud.bulk.jwt.ACSyncProductWrapperJwtRequest;
import com.annexcloud.model.AnnexCloudModel;
import com.google.common.math.DoubleMath;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.constants.CommerceServicesConstants;
import de.hybris.platform.core.model.order.AbstractOrderEntryModel;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.util.DiscountValue;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


// TODO: Auto-generated Javadoc
/**
 * The Interface AnnexCloudLoyaltyService.
 */

public interface AnnexCloudLoyaltyService
{

	/** The Constant EPSILON. */
	double EPSILON = 0.01d;

	ACUserActionDetailsResponse userActionDetailsAPI( String uid,String page);

	/**
	 * Creates the user API.
	 *
	 * @param customer
	 *           the customer
	 * @param acDetails
	 *           the ac details
	 * @return the AC response
	 */

	ACResponse createUserAPI(CustomerModel customer, final AnnexCloudModel acDetails);

	/**
	 * Update user API.
	 *
	 * @param customer
	 *           the customer
	 * @param acDetails
	 *           the ac details
	 * @return the AC user update response
	 */
	ACUserUpdateResponse updateUserAPI(ACUserUpdateJwtRequest customer, AnnexCloudModel acDetails);

	/**
	 * Opt in user API.
	 *
	 * @param customer
	 *           the customer
	 * @param acDetails
	 *           the ac details
	 * @return true, if successful
	 */
	boolean optInUserAPI(String customer, AnnexCloudModel acDetails);

	/**
	 * Gets the annex cloud credential.
	 *
	 * @return the annex cloud credential
	 */
	AnnexCloudModel getAnnexCloudCredential();

	/**
	 * Sets the bearer token.
	 *
	 * @param annex
	 *           the annex
	 * @param token
	 *           the token
	 * @param email
	 *           the email
	 */
	default void setBearerToken(final ACRequestDetails annex, final String token, final String email)
	{
		annex.setAccess_code(token);
	}

	/**
	 * Gets the bearer token.
	 *
	 * @param annex
	 *           the annex
	 * @param uid
	 *           the uid
	 * @return the bearer token
	 */
	default ACJwtRequest getBearerToken(final ACRequestDetails annex, final String uid)
	{
		final ACAbstractJwtRequest jwt = new ACAbstractJwtRequest();
		jwt.setId(uid);
		annex.setRequest_object(jwt);
		return jwt;
	}

	/**
	 * Gets the bearer token request.
	 *
	 * @param annex
	 *           the annex
	 * @param cart
	 *           the cart
	 * @return the bearer token request
	 */
	default ACCartCalculationJwtRequest getBearerTokenRequest(final ACRequestDetails annex, final CartModel cart)
	{
		CustomerModel cust = (CustomerModel) cart.getUser();
		String uidType = cart.getStore().getAnnexCloud().getAcPkType().getCode();
		String userUid = cust.getProperty(uidType);
		final ACCartCalculationJwtRequest jwt = new ACCartCalculationJwtRequest();
		jwt.setUserId(userUid);
		jwt.setAppliedPoints(String.valueOf(getLoyaltyDiscountsAmount(cart)));
		final List<ACProductDetailsJwtRequest> oEntry = new ArrayList<>();
		cart.getEntries().forEach(en -> {
			final ACProductDetailsJwtRequest acOrderProductJwtRequest = new ACProductDetailsJwtRequest();
			acOrderProductJwtRequest.setId(en.getProduct().getCode());
			acOrderProductJwtRequest.setQuantity(String.valueOf(en.getQuantity()));
			acOrderProductJwtRequest.setUnitPrice(String.valueOf(en.getBasePrice()));
			oEntry.add(acOrderProductJwtRequest);
		});
		jwt.setProductDetail(oEntry);
		annex.setRequest_object(jwt);
		return jwt;
	}

	/**
	 * Gets the total discount.
	 *
	 * @param source
	 *           the source
	 * @return the total discount
	 */
	default double getTotalDiscount(final AbstractOrderModel source)
	{
		final double pDiscounts = getProductsDiscountsAmount(source);
		final double oDiscounts = getOrderDiscountsAmount(source);
		final double lDiscounts = getLoyaltyDiscountsAmount(source);
		return pDiscounts + oDiscounts + lDiscounts;
	}

	/**
	 * Gets the loyalty discounts amount.
	 *
	 * @param source
	 *           the source
	 * @return the loyalty discounts amount
	 */
	default double getLoyaltyDiscountsAmount(final AbstractOrderModel source)
	{
		final double discounts = 0.0d;
		if (source.getAnnexCloudUserCreditPoint() != null
				&& DoubleMath.fuzzyCompare(source.getAnnexCloudUserCreditPoint().getDeductAmount(), 0, EPSILON) > 0)
		{
			return source.getAnnexCloudUserCreditPoint().getDeductAmount();
		}
		return discounts;
	}

	/**
	 * Gets the products discounts amount.
	 *
	 * @param source
	 *           the source
	 * @return the products discounts amount
	 */
	default double getProductsDiscountsAmount(final AbstractOrderModel source)
	{
		double discounts = 0.0d;

		final List<AbstractOrderEntryModel> entries = source.getEntries();
		if (entries != null)
		{
			for (final AbstractOrderEntryModel entry : entries)
			{
				final List<DiscountValue> discountValues = entry.getDiscountValues();
				if (discountValues != null)
				{
					for (final DiscountValue dValue : discountValues)
					{
						discounts += dValue.getAppliedValue();
					}
				}
			}
		}
		return discounts;
	}

	/**
	 * Gets the order discounts amount.
	 *
	 * @param source
	 *           the source
	 * @return the order discounts amount
	 */
	default double getOrderDiscountsAmount(final AbstractOrderModel source)
	{
		double discounts = 0.0d;
		final List<DiscountValue> discountList = source.getGlobalDiscountValues(); // discounts on the cart itself
		if (discountList != null && !discountList.isEmpty())
		{
			for (final DiscountValue discount : discountList)
			{
				final double value = discount.getAppliedValue();
				if (DoubleMath.fuzzyCompare(value, 0, EPSILON) > 0
						&& !CommerceServicesConstants.QUOTE_DISCOUNT_CODE.equals(discount.getCode()))
				{
					discounts += value;
				}
			}
		}
		return discounts;
	}

	/**
	 * Gets the quote discounts amount.
	 *
	 * @param source
	 *           the source
	 * @return the quote discounts amount
	 */
	default double getQuoteDiscountsAmount(final AbstractOrderModel source)
	{
		double discounts = 0.0d;
		final List<DiscountValue> discountList = source.getGlobalDiscountValues(); // discounts on the cart itself
		if (discountList != null && !discountList.isEmpty())
		{
			for (final DiscountValue discount : discountList)
			{
				final double value = discount.getAppliedValue();
				if (DoubleMath.fuzzyCompare(value, 0, EPSILON) > 0
						&& CommerceServicesConstants.QUOTE_DISCOUNT_CODE.equals(discount.getCode()))
				{
					discounts += value;
				}
			}
		}
		return discounts;
	}

	/**
	 * Gets the bearer token request.
	 *
	 * @param annex
	 *           the annex
	 * @param order
	 *           the order
	 * @return the bearer token request
	 */
	default ACOrderJwtRequest getBearerTokenRequest(final ACRequestDetails annex, final OrderModel order)
	{
		CustomerModel cust = (CustomerModel) order.getUser();
		final String uid = order.getUser().getUid();
		String uidType = order.getStore().getAnnexCloud().getAcPkType().getCode();
		String userUid = cust.getProperty(uidType);
		final ACOrderJwtRequest jwt = new ACOrderJwtRequest();
		jwt.setId(order.getCode());
		jwt.setEmail(uid);
		jwt.setUserId(userUid);
		jwt.setOrderTotal(String.valueOf(order.getSubtotal()));
		jwt.setStoreId(order.getStore().getUid());
		jwt.setDiscountAmount(String.valueOf(getTotalDiscount(order)));
		jwt.setSource(order.getSalesApplication().getCode());
		if(order.getAppliedCouponCodes()!=null && !order.getAppliedCouponCodes().isEmpty())
		{jwt.setCoupon(String.join(", ", order.getAppliedCouponCodes()));}
		final List<ACOrderProductJwtRequest> oEntry = new ArrayList<>();
		order.getEntries().forEach(en -> {
			final ACOrderProductJwtRequest acOrderProductJwtRequest = new ACOrderProductJwtRequest();
			acOrderProductJwtRequest.setId(en.getProduct().getCode());
			acOrderProductJwtRequest.setQuantity(String.valueOf(en.getQuantity()));
			acOrderProductJwtRequest.setUnitPrice(String.valueOf(en.getBasePrice()));
			oEntry.add(acOrderProductJwtRequest);
		});
		jwt.setOrderDetail(oEntry);
		annex.setRequest_object(jwt);
		return jwt;
	}

	/**
	 * Gets the bearer token.
	 *
	 * @param annex
	 *           the annex
	 * @param returnRequest
	 *           the return request
	 * @return the bearer token
	 */
	default ACReturnOrderJwtRequest getBearerToken(final ACRequestDetails annex, final ReturnRequestModel returnRequest)
	{
		final OrderModel order = returnRequest.getOrder();
		CustomerModel cust = (CustomerModel) order.getUser();
		final ACReturnOrderJwtRequest jwt = new ACReturnOrderJwtRequest();
		jwt.setOrderId(order.getCode());
		jwt.setStatus("return");
		jwt.setOrderDetail(returnRequest.getReturnEntries().stream().map(entry -> {
			final ACProductDetailsJwtRequest orderProductDetail = new ACProductDetailsJwtRequest();
			orderProductDetail.setId(entry.getOrderEntry().getProduct().getCode());
			if (entry.getExpectedQuantity() != null)
			{
				orderProductDetail.setQuantity(entry.getExpectedQuantity().toString());
			}
			if (entry.getOrderEntry().getTotalPrice() != null)
			{
				orderProductDetail.setUnitPrice(entry.getOrderEntry().getBasePrice().toString());
			}
			return orderProductDetail;
		}).collect(Collectors.toList()));
		annex.setRequest_object(jwt);
		return jwt;
	}



	/**
	 * Award deduct custom points bearer token.
	 *
	 * @param annex
	 *           the annex
	 * @param order
	 *           the order
	 * @param debit
	 *           the debit
	 * @return the AC credit debit points jwt request
	 */
	default ACCreditDebitPointsJwtRequest awardDeductCustomPointsBearerToken(final ACRequestDetails annex, final OrderModel order,
			final String debit)
	{
		CustomerModel cust = (CustomerModel) order.getUser();
		String uidType = order.getStore().getAnnexCloud().getAcPkType().getCode();
		String userUid = cust.getProperty(uidType);
		final ACCreditDebitPointsJwtRequest jwt = new ACCreditDebitPointsJwtRequest();
		jwt.setId(userUid);
		jwt.setDebit(debit);
		jwt.setActivity("DEBIT");
		jwt.setReason("Points used with order");
		jwt.setOrderId(order.getCode());
		annex.setRequest_object(jwt);
		return jwt;
	}

	/**
	 * Gets the bearer token.
	 *
	 * @param annex
	 *           the annex
	 * @param uid
	 *           the uid
	 * @param userNames
	 *           the user names
	 * @return the bearer token
	 */
	default ACUserJwtRequest getBearerToken(final ACRequestDetails annex, final String uid, final String[] userNames)
	{
		final ACUserJwtRequest jwt = new ACUserJwtRequest();
		jwt.setId(uid);
		jwt.setEmail(uid);
		jwt.setFirstName(userNames[0]);
		jwt.setLastName(userNames[1]);
		jwt.setOptInStatus("NO");
		annex.setRequest_object(jwt);
		return jwt;
	}

	/**
	 * Gets the bearer token.
	 *
	 * @param
	 * @param product
	 *           the product
	 * @return the bearer token
	 */

	default ACProductWrapperJwtRequest getBearerToken(final ProductData product)
	{
		final ACProductJwtRequest jwt = new ACProductJwtRequest();
		jwt.setId(product.getCode());
		jwt.setQuantity("1");
		jwt.setUnitPrice(product.getPrice().getValue().toString());
		jwt.setAutoDelivery("NO");
		final List<ACProductJwtRequest> productMap = new ArrayList();
		productMap.add(jwt);
		final ACProductWrapperJwtRequest productWrapper = new ACProductWrapperJwtRequest();
		productWrapper.setProductDetail(productMap);
		return productWrapper;
	}

	/**
	 * Creates the AC request.
	 *
	 * @param siteId
	 *           the site id
	 * @return the AC request details
	 */
	default ACRequestDetails createACRequest(final String siteId)
	{
		final ACRequestDetails annexCloudCreateUserRequest = new ACRequestDetails();
		annexCloudCreateUserRequest.setSiteId(siteId);
		return annexCloudCreateUserRequest;
	}

	/**
	 * Gets the AC custom action.
	 *
	 * @param baseStore
	 *           the base store
	 * @param actionName
	 *           the action name
	 * @return the AC custom action
	 */


	/**
	 * Order API.
	 *
	 * @param order
	 *           the order
	 * @return the AC response
	 */
	ACResponse orderAPI(OrderModel order);

	/**
	 * Award deduct custom points API.
	 *
	 * @param requestParameter
	 *           the request parameter
	 * @param annexSiteDetails
	 *           the annex site details
	 * @return the AC response
	 */
	ACResponse awardDeductCustomPointsAPI(ACCreditDebitPointsJwtRequest requestParameter, AnnexCloudModel annexSiteDetails);

	/**
	 * Creates the token.
	 *
	 * @param uid
	 *           the uid
	 * @param annex
	 *           the annex
	 * @return the string
	 */
	String createToken(String uid, AnnexCloudModel annex);

	/**
	 * Product information API.
	 *
	 * @param jwt
	 *           the jwt
	 * @param acDetails
	 *           the ac details
	 * @return the AC product point wrapper response
	 */
	ACProductPointWrapperResponse productInformationAPI(final ACProductWrapperJwtRequest jwt, final AnnexCloudModel acDetails);

	/**
	 * Return order API.
	 *
	 * @param returnRequest
	 *           the return request
	 * @return the AC return orcer response
	 */
	ACReturnOrcerResponse returnOrderAPI(ReturnRequestModel returnRequest);

	/**
	 * Gets the eligible reward list API.
	 *
	 * @param customer
	 *           the customer
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the eligible reward list API
	 */
	ACRewardListResponse getEligibleRewardListAPI(String customer, AnnexCloudModel annexCloudModel);

	/**
	 * Save credit point in cart.
	 *
	 * @param rewardId
	 *           the reward id
	 * @param deductAmount
	 *           the deduct amount
	 */
	void saveCreditPointInCart(String rewardId, Double deductAmount);

	/**
	 * View points detail API.
	 *
	 * @param customer
	 *           the customer
	 * @param annex
	 *           the annex
	 * @return the AC user points response
	 */
	ACUserPointsResponse viewPointsDetailAPI(String customer, AnnexCloudModel annex);

	/**
	 * Cart calculation API.
	 *
	 * @param order
	 *           the order
	 * @param annex
	 *           the annex
	 * @return the AC cart calculation response
	 */
	ACCartCalculationResponse cartCalculationAPI(ACCartCalculationJwtRequest order, AnnexCloudModel annex);

	/**
	 * User details API.
	 *
	 * @param customer
	 *           the customer
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the AC user details response
	 */
	ACUserDetailsResponse userDetailsAPI(String customer, AnnexCloudModel annexCloudModel);

	/**
	 * Gets the used reward list API.
	 *
	 * @param customer
	 *           the customer
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the used reward list API
	 */
	ACUsedRewardListResponse getUsedRewardListAPI(String customer, AnnexCloudModel annexCloudModel,String page);

	/**
	 * User activity API.
	 *
	 * @param customer
	 *           the customer
	 * @param page
	 *           the page
	 * @return the AC user activity response
	 */
	ACUserActivityResponse userActivityAPI(String customer, String page);

	/**
	 * Segment detail API.
	 *
	 * @param customer
	 *           the customer
	 * @return the AC segment details wrapper response
	 */
	ACSegmentDetailsWrapperResponse segmentDetailAPI(String customer);

	ACCreateSegmentResponse createSegmentAPI(ACCreateSegmentJwtRequest segmentJwtRequest, AnnexCloudModel annexCloudModel);

	ACUpdateSegmentResponse updateSegmentAPI(ACUpdateSegmentJwtRequest segmentJwtRequest, AnnexCloudModel annexCloudModel);
	/**
	 * Store detail API.
	 *
	 * @param customer
	 *           the customer
	 * @return the AC store details response
	 */
	ACStoreDetailsResponse storeDetailAPI(String customer);

	/**
	 * Campaign detail API.
	 *
	 * @param customer
	 *           the customer
	 * @return the AC campaign details response
	 */
	ACCampaignDetailsResponse campaignDetailAPI(String customer);

	/**
	 * All action detail API.
	 *
	 * @return the AC all action detail response
	 */
	ACAllActionDetailResponse allActionDetailAPI();

	/**
	 *
	 */
	ACTierInformationResponse tierInformationAPI(String customer);

	ACUserUpdateResponse optInUserAPI(final ACUserUpdateJwtRequest optInUserRequest , final AnnexCloudModel acDetails);

	ResponseEntity<Object> createBulkProductsAPI(AnnexCloudModel acDetails, Map<String, ACSyncProductWrapperJwtRequest> products);

	ACResponse updateProductAPI(AnnexCloudModel acDetails,ACSyncProductWrapperJwtRequest products);

	ResponseEntity<Object> getBulkUserPointsAPI(Map<Integer, ACAbstractJwtRequest> customers, AnnexCloudModel annexCloudModel);

	ResponseEntity<Object> getBulkUserTiersAPI(Map<Integer, ACAbstractJwtRequest> customers, AnnexCloudModel annexCloudModel);
}
