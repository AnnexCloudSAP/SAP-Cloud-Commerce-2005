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

package com.annexcloud.service.impl;
import com.annex.cloud.loyalty.ACRequestDetails;
import com.annex.cloud.loyalty.jwt.*;
import com.annex.cloud.loyalty.jwt.order.ACOrderJwtRequest;
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
import com.annexcloud.AnnexCloudJWTService;
import com.annexcloud.bulk.jwt.ACSyncProductWrapperJwtRequest;
import com.annexcloud.customer.dao.Impl.ACCustomerDaoImpl;
import com.annexcloud.enums.CreditDebitType;
import com.annexcloud.event.LoyaltyDataEvent;
import com.annexcloud.model.AnnexCloudModel;
import com.annexcloud.model.AnnexCloudUserCreditPointModel;
import com.annexcloud.service.AnnexCloudLoyaltyService;
import com.annexcloud.service.AnnexCloudRestIntegrationService;
import com.annexcloud.service.AnnexCloudUniqueUidService;
import de.hybris.platform.commerceservices.order.CommerceCartCalculationStrategy;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.returns.model.ReturnRequestModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static com.annexcloud.constants.AnnexCloudV3Constants.*;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultAnnexCloudLoyaltyV3Service.
 */
public class DefaultAnnexCloudLoyaltyV3Service implements AnnexCloudLoyaltyService
{
	/** The Constant EMPTY_USER. */
	public static final String EMPTY_USER = "";

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(DefaultAnnexCloudLoyaltyV3Service.class);

	/** The base store service. */
	private BaseStoreService baseStoreService;

	/** The common I 18 N service. */
	private CommonI18NService commonI18NService;

	/** The model service. */
	private ModelService modelService;

	/** The annex cloud rest system service factory. */
	private DefaultAnnexCloudSystemServiceFactory annexCloudRestSystemServiceFactory;

	/** The session service. */
	private SessionService sessionService;

	/** The commerce cart calculation strategy. */
	private CommerceCartCalculationStrategy commerceCartCalculationStrategy;

	/** The customer name strategy. */
	private CustomerNameStrategy customerNameStrategy;

	/** The annex cloud JWT service. */
	private AnnexCloudJWTService annexCloudJWTService;

	private AnnexCloudUniqueUidService annexCloudUniqueUidService;

	private UserService userService;

	private ACCustomerDaoImpl defaultACCustomerDao;

	private EventService eventService;



	/**
	 * Gets the annex cloud credential.
	 *
	 * @return the annex cloud credential
	 */
	@Override
	public AnnexCloudModel getAnnexCloudCredential()
	{
		return getBaseStoreService().getCurrentBaseStore().getAnnexCloud();
	}

	/**
	 * Creates the token.
	 *
	 * @param uid
	 *           the uid
	 * @param annex
	 *           the annex
	 * @return the string
	 */
	@Override
	public String createToken(final String uid, final AnnexCloudModel annex)
	{
		return annexCloudJWTService.createToken(uid, annex);
	}

	/**
	 * Order API.
	 *
	 * @param order
	 *           the order
	 * @return the AC response
	 */
	@Override
	public ACResponse orderAPI(final OrderModel order)
	{
		//get credential
		final Map<String, String> params = new HashMap<>();
		CustomerModel cust = (CustomerModel) order.getUser();
		String typeOfUid = order.getStore().getAnnexCloud().getAcPkType().getCode();
		String userUid = cust.getProperty(typeOfUid);
		ACResponse createOrderInAnnexCloudResponse = null;
		final AnnexCloudModel acDetails = order.getStore().getAnnexCloud();
		//create request object
		final ACRequestDetails createOrderInAnnexCloudRequest = createACRequest(acDetails.getSiteId());
		//get token object
		final ACOrderJwtRequest jwt = getBearerTokenRequest(createOrderInAnnexCloudRequest, order);
		//set token
		setBearerToken(createOrderInAnnexCloudRequest,
				annexCloudJWTService.createBearerToken(jwt, order.getStore().getAnnexCloud()), userUid);
		createOrderInAnnexCloudRequest.setParams(params);
		//get respective service
		final DefaultAnnexCloudV3RestIntegrationService annexCloudCreateOrderLoyaltyService = annexCloudRestSystemServiceFactory
				.getSystemService(CRETE_ORDER_SERVICE_CODE);
		// call the service
		createOrderInAnnexCloudResponse = annexCloudCreateOrderLoyaltyService.execute(createOrderInAnnexCloudRequest);
		return createOrderInAnnexCloudResponse;
	}

	/**
	 * Cart calculation API.
	 *
	 * @param jwt
	 *           the jwt
	 * @param acDetails
	 *           the ac details
	 * @return the AC cart calculation response
	 */
	@Override
	public ACCartCalculationResponse cartCalculationAPI(final ACCartCalculationJwtRequest jwt, final AnnexCloudModel acDetails)
	{
		final Map<String, String> params = new HashMap<>();
		//create request object
		final ACRequestDetails createOrderInAnnexCloudRequest = createACRequest(acDetails.getSiteId());
		createOrderInAnnexCloudRequest.setRequest_object(jwt);
		//set token
		setBearerToken(createOrderInAnnexCloudRequest, annexCloudJWTService.createBearerToken(jwt), annexCloudUniqueUidService.getACUniqueId());
		createOrderInAnnexCloudRequest.setParams(params);
		//get respective service
		final DefaultAnnexCloudV3RestIntegrationService annexCloudCreateOrderLoyaltyService = annexCloudRestSystemServiceFactory
				.getSystemService(CALCULATE_CART_POINTS_SERVICE);
		// call the service
		final ACResponse createOrderInAnnexCloudResponse = annexCloudCreateOrderLoyaltyService
				.execute(createOrderInAnnexCloudRequest);
		return (ACCartCalculationResponse) createOrderInAnnexCloudResponse;
	}

	/**
	 * Return order API.
	 *
	 * @param returnRequest
	 *           the return request
	 * @return the AC return orcer response
	 */
	@Override
	public ACReturnOrcerResponse returnOrderAPI(final ReturnRequestModel returnRequest)
	{
		final Map<String, String> params = new HashMap<>();
		//get credential
		final OrderModel order = returnRequest.getOrder();
		CustomerModel cust = (CustomerModel) order.getUser();
		final AnnexCloudModel acDetails = order.getStore().getAnnexCloud();
		String uidType = order.getStore().getAnnexCloud().getAcPkType().getCode();
		LOG.info("Type OF Uid-----------" + uidType);
		String userUid = cust.getProperty(uidType);
		//create request object
		final ACRequestDetails returnOrderInAnnexCloudRequest = createACRequest(acDetails.getSiteId());
		//get token object
		final ACReturnOrderJwtRequest jwt = getBearerToken(returnOrderInAnnexCloudRequest, returnRequest);
		//create bearer token
		setBearerToken(returnOrderInAnnexCloudRequest,
				annexCloudJWTService.createBearerToken(jwt, order.getStore().getAnnexCloud()), userUid);
		params.put(ID, order.getCode());
		returnOrderInAnnexCloudRequest.setParams(params);
		//get respective service
		final DefaultAnnexCloudV3RestIntegrationService annexCloudReturnOrderLoyaltyService = annexCloudRestSystemServiceFactory
				.getSystemService(RETURN_ORDER_SERVICE_CODE);
		// call the service
		final ACReturnOrcerResponse returnOrderInAnnexCloudResponse = (ACReturnOrcerResponse) annexCloudReturnOrderLoyaltyService
				.execute(returnOrderInAnnexCloudRequest);
		return returnOrderInAnnexCloudResponse;
	}

	/**
	 * Gets the eligible reward list API.
	 *
	 * @param customer
	 *           the customer
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the eligible reward list API
	 */
	@Override
	public ACRewardListResponse getEligibleRewardListAPI(final String customer, final AnnexCloudModel annexCloudModel)
	{
		final Map<String, String> params = new HashMap<>();
		//create request object
		final ACRequestDetails annexCloudRewardListRequest = createACRequest(annexCloudModel.getSiteId());
		//create token
		final String token = "Bearer " + this.createToken(customer, annexCloudModel);
		//Set token
		setBearerToken(annexCloudRewardListRequest, token, customer);
		params.put(ID, customer);
		annexCloudRewardListRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(REWARD_SERVICE);
		// call the service
		return (ACRewardListResponse) annexCloudUserRewardListService.execute(annexCloudRewardListRequest);
	}

	/**
	 * Gets the used reward list API.
	 *
	 * @param customer
	 *           the customer
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the used reward list API
	 */
	@Override
	public ACUsedRewardListResponse getUsedRewardListAPI(final String customer, final AnnexCloudModel annexCloudModel,final String page)
	{
		final Map<String, String> params = new HashMap<>();
		//create request object
		final ACRequestDetails annexCloudRewardListRequest = createACRequest(annexCloudModel.getSiteId());
		//create token
		final String token = "Bearer " + this.createToken(customer, annexCloudModel);
		//Set token
		setBearerToken(annexCloudRewardListRequest, token, customer);
		params.put(ID, customer);
		params.put(PAGE_NO,page);
		annexCloudRewardListRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(USED_REWARD_SERVICE);
		// call the service
		return (ACUsedRewardListResponse) annexCloudUserRewardListService.execute(annexCloudRewardListRequest);
	}

	/**
	 * View points detail API.
	 *
	 * @param customer
	 *           the customer
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the AC user points response
	 */
	@Override
	public ACUserPointsResponse viewPointsDetailAPI(final String customer, final AnnexCloudModel annexCloudModel)
	{
		final Map<String, String> params = new HashMap<>();
		final ACRequestDetails annexCloudRewardListRequest = createACRequest(annexCloudModel.getSiteId());
		//create token
		final String token = "Bearer " + this.createToken(customer, annexCloudModel);
		//Set token
		setBearerToken(annexCloudRewardListRequest, token, customer);
		params.put(ID, customer);
		annexCloudRewardListRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(USER_POINTS_SERVICE);
		// call the service
		return (ACUserPointsResponse) annexCloudUserRewardListService.execute(annexCloudRewardListRequest);
	}

	/**
	 * User details API.
	 *
	 * @param customer
	 *           the customer
	 * @param annexCloudModel
	 *           the annex cloud model
	 * @return the AC user details response
	 */
	@Override
	public ACUserDetailsResponse userDetailsAPI(final String customer, final AnnexCloudModel annexCloudModel)
	{
		final Map<String, String> params = new HashMap<>();
		final ACRequestDetails annexCloudRewardListRequest = createACRequest(annexCloudModel.getSiteId());
		//create token
		final String token = "Bearer " + this.createToken(customer, annexCloudModel);
		//Set token
		setBearerToken(annexCloudRewardListRequest, token, customer);
		params.put(ID, customer);
		annexCloudRewardListRequest.setParams(params);
		//
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(USER_ACTIVE_STATUS_CHECHK_SERVICE);
		// call the service
		ACUserDetailsResponse acUserDetailsResponse = (ACUserDetailsResponse)annexCloudUserRewardListService.execute(annexCloudRewardListRequest);
		if(acUserDetailsResponse.getRequestHasSucceeded())
		{
			LoyaltyDataEvent loyaltyDataEvent = new LoyaltyDataEvent(acUserDetailsResponse);
			loyaltyDataEvent.setAcUserDetailsResponse(acUserDetailsResponse);
			eventService.publishEvent(loyaltyDataEvent);
		}
		return acUserDetailsResponse;
	}

	/**
	 * Award deduct custom points API.
	 *
	 * @param requestParameter
	 *           the request parameter
	 * @param annexSiteDetails
	 *           the annex site details
	 * @return the AC response
	 */
	@Override
	public ACResponse awardDeductCustomPointsAPI(final ACCreditDebitPointsJwtRequest requestParameter,
			final AnnexCloudModel annexSiteDetails)
	{
		final Map<String, String> params = new HashMap<>();
		final ACRequestDetails annexCloudCustomActionRequest = createACRequest(annexSiteDetails.getSiteId());
		annexCloudCustomActionRequest.setRequest_object(requestParameter);
		setBearerToken(annexCloudCustomActionRequest, annexCloudJWTService.createBearerToken(requestParameter, annexSiteDetails),
				requestParameter.getId());
		annexCloudCustomActionRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudCustomActionLoyaltyService = annexCloudRestSystemServiceFactory
				.getSystemService(CUSTOM_ACTION_SERVICE_CODE);

		final ACResponse annexCloudCustomActionResponse = annexCloudCustomActionLoyaltyService
				.execute(annexCloudCustomActionRequest);
		return annexCloudCustomActionResponse;
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
		final CartModel cartModel = getSessionService().getAttribute("cart");
			if(deductAmount<=cartModel.getSubtotal().doubleValue())
			{
				final AnnexCloudUserCreditPointModel annexCloudUserCreditPointModel = getModelService()
						.create(AnnexCloudUserCreditPointModel.class);
				annexCloudUserCreditPointModel.setRewardId(rewardId);
				annexCloudUserCreditPointModel.setDeductAmount(deductAmount);
				cartModel.setAnnexCloudUserCreditPoint(annexCloudUserCreditPointModel);
				getModelService().saveAll();
				getCommerceCartCalculationStrategy().recalculateCart(cartModel);
			}
	}

	/**
	 * Product information API.
	 *
	 * @param jwt
	 *           the jwt
	 * @param acDetails
	 *           the ac details
	 * @return the AC product point wrapper response
	 */
	@Override
	public ACProductPointWrapperResponse productInformationAPI(final ACProductWrapperJwtRequest jwt,
			final AnnexCloudModel acDetails)
	{
		final Map<String, String> params = new HashMap<>();
		//add site details to request object
		final ACRequestDetails annexCloudProductPointsRequest = createACRequest(acDetails.getSiteId());
		//add product data to request
		annexCloudProductPointsRequest.setRequest_object(jwt);
		//add authorization token to request
		setBearerToken(annexCloudProductPointsRequest, annexCloudJWTService.createBearerToken(jwt), annexCloudUniqueUidService.getACUniqueId());
		annexCloudProductPointsRequest.setParams(params);
		//get service bean for product API
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserOptInService = annexCloudRestSystemServiceFactory
				.getSystemService(PRODUCT_POINT_SERVICE);
		// call the service
		return (ACProductPointWrapperResponse) annexCloudUserOptInService.execute(annexCloudProductPointsRequest);
	}


	@Override
	public ACUserActionDetailsResponse userActionDetailsAPI(final String uid,String page )
	{
		final Map<String, String> params = new HashMap<>();
		final AnnexCloudModel acDetails = getAnnexCloudCredential();
		//add site details to request object
		ACUserActionJwtRequest jwt = new ACUserActionJwtRequest();
		jwt.setId(uid);
		jwt.setPage(page);

		final ACRequestDetails annexCloudProductPointsRequest = createACRequest(acDetails.getSiteId());
		//add product data to request
		annexCloudProductPointsRequest.setRequest_object(jwt);
		//add authorization token to request
		setBearerToken(annexCloudProductPointsRequest, annexCloudJWTService.createBearerToken(jwt), jwt.getId());
		params.put(PAGE_NO, page);
		annexCloudProductPointsRequest.setParams(params);
		//get service bean for user actions API
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserOptInService = annexCloudRestSystemServiceFactory
				.getSystemService(USER_ACTION_DETAILS_SERVICE);
		// call the service
		return (ACUserActionDetailsResponse) annexCloudUserOptInService.execute(annexCloudProductPointsRequest);
		//return null;
	}

	/**
	 * Creates the user API.
	 *
	 * @param customer
	 *           the customer
	 * @param acDetails
	 *           the ac details
	 * @return the AC response
	 */
	@Override
	public ACResponse createUserAPI(final CustomerModel customer, final AnnexCloudModel acDetails)
	{
		final Map<String, String> params = new HashMap<>();
		String uid = annexCloudUniqueUidService.getACUniqueId(acDetails,customer);
		final ACResponse annexUserResponse = null;
		final ACRequestDetails acUserRequest = createACRequest(acDetails.getSiteId());
		final ACUserJwtRequest jwt = getBearerToken(acUserRequest, 	uid,
				customerNameStrategy.splitName(customer.getName()));
		setBearerToken(acUserRequest, annexCloudJWTService.createBearerToken(jwt), uid);
		acUserRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService ac = annexCloudRestSystemServiceFactory
				.getSystemService(CRAETE_USER_SERVICE);
		final ACResponse response = ac.execute(acUserRequest);
		if (response.getRequestHasSucceeded())
		{
		}
		return response;
	}

	/**
	 * Update user API.
	 *
	 * @param customer
	 *           the customer
	 * @param acDetails
	 *           the ac details
	 * @return the AC user update response
	 */
	@Override
	public ACUserUpdateResponse updateUserAPI(final ACUserUpdateJwtRequest customer, final AnnexCloudModel acDetails)
	{
		final Map<String, String> params = new HashMap<>();
		final ACUserUpdateResponse annexUserResponse = null;
		final ACRequestDetails acUserRequest = createACRequest(acDetails.getSiteId());
		acUserRequest.setRequest_object(customer);
		setBearerToken(acUserRequest, annexCloudJWTService.createBearerToken(customer, acDetails), customer.getId());
		params.put(ID, customer.getId());
		acUserRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService ac = annexCloudRestSystemServiceFactory
				.getSystemService(UPDATE_USER_SERVICE);
		ACUserUpdateResponse acUserUpdateResponse = (ACUserUpdateResponse) ac.execute(acUserRequest);
		return acUserUpdateResponse;
	}

	/**
	 * Opt in user API.
	 *
	 * @param customer
	 *           the customer
	 * @param acDetails
	 *           the ac details
	 * @return true, if successful
	 */
	@Override
	public boolean optInUserAPI(final String customer, final AnnexCloudModel acDetails)
	{
		final ACUserUpdateJwtRequest optInUserRequest = new ACUserUpdateJwtRequest();
		optInUserRequest.setId(customer);
		optInUserRequest.setEmail(customer);
		optInUserRequest.setOptInStatus("YES");
		final ACUserUpdateResponse response = this.updateUserAPI(optInUserRequest, acDetails);
		if (response.getRequestHasSucceeded())
		{
			final ACCreditDebitPointsJwtRequest acCreditDebitPointsJwtRequest = new ACCreditDebitPointsJwtRequest();
			acCreditDebitPointsJwtRequest.setActivity(CreditDebitType.CREDIT.getCode());
			acCreditDebitPointsJwtRequest.setId(customer);
			acCreditDebitPointsJwtRequest.setActionId("101");
			final ACResponse annexCloudCustomActionResponse = this.awardDeductCustomPointsAPI(acCreditDebitPointsJwtRequest,
					acDetails);
			if (!annexCloudCustomActionResponse.getRequestHasSucceeded())
			{
				LOG.debug("Failed to earn loyalty point due to---" + annexCloudCustomActionResponse.getErrorMessage());
			}
			return annexCloudCustomActionResponse.getRequestHasSucceeded() ? true : false;
		}
		else
		{
			return false;
		}
	}

	@Override
	public ACUserUpdateResponse optInUserAPI(final ACUserUpdateJwtRequest optInUserRequest , final AnnexCloudModel acDetails)
	{

		final ACUserUpdateResponse response = this.updateUserAPI(optInUserRequest, acDetails);
		if (response.getRequestHasSucceeded() && response.getOptInStatus().equals("YES"))
		{
			final ACCreditDebitPointsJwtRequest acCreditDebitPointsJwtRequest = new ACCreditDebitPointsJwtRequest();
			acCreditDebitPointsJwtRequest.setActivity(CreditDebitType.CREDIT.getCode());
			acCreditDebitPointsJwtRequest.setId(optInUserRequest.getId());
			acCreditDebitPointsJwtRequest.setActionId("101");

			final ACResponse annexCloudCustomActionResponse = this.awardDeductCustomPointsAPI(acCreditDebitPointsJwtRequest,
					acDetails);
			if (!annexCloudCustomActionResponse.getRequestHasSucceeded())
			{
				LOG.debug("Failed to earn loyalty point due to---" + annexCloudCustomActionResponse.getErrorMessage());
			}

		}


		return response;

	}


	/**
	 * User activity API.
	 *
	 * @param customer
	 *           the customer
	 * @param page
	 *           the page
	 * @return the AC user activity response
	 */
	@Override
	public ACUserActivityResponse userActivityAPI(final String customer, final String page)
	{
		final Map<String, String> params = new HashMap<>();
		final AnnexCloudModel annex = getAnnexCloudCredential();
		final ACRequestDetails acUserActivity = createACRequest(annex.getSiteId());
		final String token = "Bearer " + this.createToken(customer, annex);
		setBearerToken(acUserActivity, token, customer);
		params.put(ID, customer);
		params.put(PAGE_NO, page);
		acUserActivity.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(USER_ACTIVITY_CODE);
		return (ACUserActivityResponse) annexCloudUserRewardListService.execute(acUserActivity);
	}

	/**
	 * Segment detail API.
	 *
	 * @param customer
	 *           the customer
	 * @return the AC segment details wrapper response
	 */
	@Override
	public ACSegmentDetailsWrapperResponse segmentDetailAPI(final String customer)
	{
		final Map<String, String> params = new HashMap<>();
		final AnnexCloudModel annex = getAnnexCloudCredential();
		final ACRequestDetails acUserActivity = createACRequest(annex.getSiteId());
		final String token = "Bearer " + this.createToken(customer, annex);
		setBearerToken(acUserActivity, token, customer);
		params.put(ID, customer);
		acUserActivity.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(SEGMENT_DETAILS_API);
		return (ACSegmentDetailsWrapperResponse) annexCloudUserRewardListService.execute(acUserActivity);
	}

	@Override
	public ACCreateSegmentResponse createSegmentAPI(ACCreateSegmentJwtRequest segmentJwtRequest, AnnexCloudModel annexCloudModel) {
		final Map<String, String> params = new HashMap<>();
		final ACRequestDetails acUserRequest = createACRequest(annexCloudModel.getSiteId());
		acUserRequest.setRequest_object(segmentJwtRequest);
		setBearerToken(acUserRequest, annexCloudJWTService.createBearerToken(segmentJwtRequest, annexCloudModel), "");
		acUserRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService ac = annexCloudRestSystemServiceFactory
				.getSystemService(CREATE_SEGMENT);
		return (ACCreateSegmentResponse) ac.execute(acUserRequest);
	}

	@Override
	public ACUpdateSegmentResponse updateSegmentAPI(ACUpdateSegmentJwtRequest segmentJwtRequest, AnnexCloudModel annexCloudModel) {
		final Map<String, String> params = new HashMap<>();
		final ACRequestDetails acUserRequest = createACRequest(annexCloudModel.getSiteId());
		acUserRequest.setRequest_object(segmentJwtRequest);
		setBearerToken(acUserRequest, annexCloudJWTService.createBearerToken(segmentJwtRequest, annexCloudModel), "");
		params.put(ID, segmentJwtRequest.getId());
		acUserRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService ac = annexCloudRestSystemServiceFactory
				.getSystemService(UPDATE_SEGMENT);
		return (ACUpdateSegmentResponse) ac.execute(acUserRequest);
	}

	/**
	 * Store detail API.
	 *
	 * @param customer
	 *           the customer
	 * @return the AC store details response
	 */
	@Override
	public ACStoreDetailsResponse storeDetailAPI(final String customer)
	{
		final Map<String, String> params = new HashMap<>();
		final AnnexCloudModel annex = getAnnexCloudCredential();
		final ACRequestDetails acUserActivity = createACRequest(annex.getSiteId());
		final String token = "Bearer " + this.createToken(customer, annex);
		setBearerToken(acUserActivity, token, customer);
		params.put(ID, customer);
		acUserActivity.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(STORE_DETAILS_API);
		return (ACStoreDetailsResponse) annexCloudUserRewardListService.execute(acUserActivity);
	}

	/**
	 * Campaign detail API.
	 *
	 * @param customer
	 *           the customer
	 * @return the AC campaign details response
	 */
	@Override
	public ACCampaignDetailsResponse campaignDetailAPI(final String customer)
	{
		final Map<String, String> params = new HashMap<>();
		final AnnexCloudModel annex = getAnnexCloudCredential();
		final ACRequestDetails acUserActivity = createACRequest(annex.getSiteId());
		final String token = "Bearer " + this.createToken(customer, annex);
		setBearerToken(acUserActivity, token, customer);
		params.put(ID, customer);
		acUserActivity.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(CAMPAIGN_DETAILS_API);
		return (ACCampaignDetailsResponse) annexCloudUserRewardListService.execute(acUserActivity);
	}

	@Override
	public ACTierInformationResponse tierInformationAPI(final String customer)
	{
		final Map<String, String> params = new HashMap<>();
		final AnnexCloudModel annex = getAnnexCloudCredential();
		final ACRequestDetails acUserActivity = createACRequest(annex.getSiteId());
		final String token = "Bearer " + this.createToken(customer, annex);
		setBearerToken(acUserActivity, token, customer);
		params.put(ID, customer);
		acUserActivity.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(TIER_INFORMATION_API);
		return (ACTierInformationResponse) annexCloudUserRewardListService.execute(acUserActivity);
	}

	/**
	 * All action detail API.
	 *
	 * @return the AC all action detail response
	 */
	@Override
	public ACAllActionDetailResponse allActionDetailAPI()
	{
		final Map<String, String> params = new HashMap<>();
		final AnnexCloudModel annex = getAnnexCloudCredential();
		final ACRequestDetails acUserActivity = createACRequest(annex.getSiteId());
		final String token = "Bearer " + this.createToken("ALL", annex);
		setBearerToken(acUserActivity, token, EMPTY_USER);
		acUserActivity.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(ALL_ACTION_DETAILS_API);
		return (ACAllActionDetailResponse) annexCloudUserRewardListService.execute(acUserActivity);
	}

	@Override
	public ResponseEntity<Object> createBulkProductsAPI(AnnexCloudModel acDetails, Map<String, ACSyncProductWrapperJwtRequest> products) {
		final Map<String, String> params = new HashMap<>();
		final ACRequestDetails createProductsInAnnexCloudRequest = createACRequest(acDetails.getSiteId());
		createProductsInAnnexCloudRequest.setRequest_object(products);
		final String token = annexCloudJWTService.createBearerToken(products,acDetails);
		createProductsInAnnexCloudRequest.setAccess_code(token);
		createProductsInAnnexCloudRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudCreateOrderLoyaltyService = annexCloudRestSystemServiceFactory
				.getSystemService(BULK_PRODUCTS_CODE);
		return annexCloudCreateOrderLoyaltyService.upload(createProductsInAnnexCloudRequest);
	}

	@Override
	public ACResponse updateProductAPI(AnnexCloudModel acDetails,ACSyncProductWrapperJwtRequest product) {
		final Map<String, String> params = new HashMap<>();
		params.put(ID,product.getProductDetail().getProductId());
		final ACRequestDetails createProductsInAnnexCloudRequest = createACRequest(acDetails.getSiteId());
		createProductsInAnnexCloudRequest.setRequest_object(product);
		final String token = annexCloudJWTService.createBearerToken(product,acDetails);
		createProductsInAnnexCloudRequest.setAccess_code(token);
		createProductsInAnnexCloudRequest.setParams(params);
		final AnnexCloudRestIntegrationService acLoyaltyService
				= annexCloudRestSystemServiceFactory.getSystemService(UPDATE_PRODUCT_CODE);
		return acLoyaltyService.execute(createProductsInAnnexCloudRequest);
	}

	@Override
	public ResponseEntity<Object> getBulkUserPointsAPI(Map<Integer, ACAbstractJwtRequest> customes, AnnexCloudModel annexCloudModel) {
		final Map<String, String> params = new HashMap<>();
		//create request object
		final ACRequestDetails annexCloudRewardListRequest = createACRequest(annexCloudModel.getSiteId());
		//create token
		annexCloudRewardListRequest.setRequest_object(customes);
		final String token = annexCloudJWTService.createBearerToken(customes,annexCloudModel);
		//Set token
		annexCloudRewardListRequest.setAccess_code(token);
		annexCloudRewardListRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(BULK_POINTS_CODE);
		// call the service
		return annexCloudUserRewardListService.upload(annexCloudRewardListRequest);

	}

	@Override
	public ResponseEntity<Object> getBulkUserTiersAPI(Map<Integer, ACAbstractJwtRequest> customes, AnnexCloudModel annexCloudModel) {
		final Map<String, String> params = new HashMap<>();
		//create request object
		final ACRequestDetails annexCloudRewardListRequest = createACRequest(annexCloudModel.getSiteId());
		//create token
		annexCloudRewardListRequest.setRequest_object(customes);
		final String token = annexCloudJWTService.createBearerToken(customes,annexCloudModel);
		//Set token
		annexCloudRewardListRequest.setAccess_code(token);
		annexCloudRewardListRequest.setParams(params);
		final DefaultAnnexCloudV3RestIntegrationService annexCloudUserRewardListService = annexCloudRestSystemServiceFactory
				.getSystemService(BULK_TIERS_CODE);
		// call the service
		return annexCloudUserRewardListService.upload(annexCloudRewardListRequest);

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
	 * Gets the annex cloud rest system service factory.
	 *
	 * @return the annex cloud rest system service factory
	 */
	public DefaultAnnexCloudSystemServiceFactory getAnnexCloudRestSystemServiceFactory()
	{
		return annexCloudRestSystemServiceFactory;
	}

	/**
	 * Sets the annex cloud rest system service factory.
	 *
	 * @param annexCloudRestSystemServiceFactory
	 *           the new annex cloud rest system service factory
	 */
	@Required
	public void setAnnexCloudRestSystemServiceFactory(
			final DefaultAnnexCloudSystemServiceFactory annexCloudRestSystemServiceFactory)
	{
		this.annexCloudRestSystemServiceFactory = annexCloudRestSystemServiceFactory;
	}

	/**
	 * Gets the session service.
	 *
	 * @return the session service
	 */
	public SessionService getSessionService()
	{
		return sessionService;
	}

	/**
	 * Sets the session service.
	 *
	 * @param sessionService
	 *           the new session service
	 */
	@Required
	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	/**
	 * Gets the commerce cart calculation strategy.
	 *
	 * @return the commerce cart calculation strategy
	 */
	public CommerceCartCalculationStrategy getCommerceCartCalculationStrategy()
	{
		return commerceCartCalculationStrategy;
	}

	/**
	 * Sets the commerce cart calculation strategy.
	 *
	 * @param commerceCartCalculationStrategy
	 *           the new commerce cart calculation strategy
	 */
	@Required
	public void setCommerceCartCalculationStrategy(final CommerceCartCalculationStrategy commerceCartCalculationStrategy)
	{
		this.commerceCartCalculationStrategy = commerceCartCalculationStrategy;
	}

	/**
	 * Gets the base store service.
	 *
	 * @return the base store service
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * Sets the base store service.
	 *
	 * @param baseStoreService
	 *           the new base store service
	 */
	@Required
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * Gets the common I 18 N service.
	 *
	 * @return the common I 18 N service
	 */
	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	/**
	 * Sets the common I 18 N service.
	 *
	 * @param commonI18NService
	 *           the new common I 18 N service
	 */
	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	/**
	 * Gets the customer name strategy.
	 *
	 * @return the customer name strategy
	 */
	public CustomerNameStrategy getCustomerNameStrategy()
	{
		return customerNameStrategy;
	}

	/**
	 * Sets the customer name strategy.
	 *
	 * @param customerNameStrategy
	 *           the new customer name strategy
	 */
	@Required
	public void setCustomerNameStrategy(final CustomerNameStrategy customerNameStrategy)
	{
		this.customerNameStrategy = customerNameStrategy;
	}

	/**
	 * Gets the annex cloud JWT service.
	 *
	 * @return the annex cloud JWT service
	 */
	public AnnexCloudJWTService getAnnexCloudJWTService()
	{
		return annexCloudJWTService;
	}

	/**
	 * Sets the annex cloud JWT service.
	 *
	 * @param annexCloudJWTService
	 *           the new annex cloud JWT service
	 */
	@Required
	public void setAnnexCloudJWTService(final AnnexCloudJWTService annexCloudJWTService)
	{
		this.annexCloudJWTService = annexCloudJWTService;
	}

	public AnnexCloudUniqueUidService getAnnexCloudUniqueUidService() {
		return annexCloudUniqueUidService;
	}

	public void setAnnexCloudUniqueUidService(AnnexCloudUniqueUidService annexCloudUniqueUidService) {
		this.annexCloudUniqueUidService = annexCloudUniqueUidService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ACCustomerDaoImpl getDefaultACCustomerDao() {
		return defaultACCustomerDao;
	}

	public void setDefaultACCustomerDao(ACCustomerDaoImpl defaultACCustomerDao) {
		this.defaultACCustomerDao = defaultACCustomerDao;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}





}
