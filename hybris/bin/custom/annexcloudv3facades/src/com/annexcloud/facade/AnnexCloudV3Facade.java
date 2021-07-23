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
package com.annexcloud.facade;

import com.annex.cloud.v3.api.response.user.ACUserDetailsResponse;
import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;

import com.annex.cloud.data.customer.AnnexCloudData;
import com.annex.cloud.data.reward.AnnexRewardData;
import com.annex.cloud.data.reward.AnnexUserPointsData;
import com.annex.cloud.product.data.AnnexCloudProductDetailsData;
import com.annex.cloud.sitedetails.ACSiteDetailsOptions;
import com.annex.cloud.v3.api.response.ACResponse;
import com.annex.cloud.v3.api.response.user.ACCartCalculationResponse;
import com.annex.cloud.v3.api.response.user.ACCustomActionResponse;


// TODO: Auto-generated Javadoc
/**
 * The Interface AnnexCloudV3Facade.
 */
public interface AnnexCloudV3Facade
{

	/**
	 * Gets the product purchase points.
	 *
	 * @param productData
	 *           the product data
	 * @return the product purchase points
	 */
	AnnexCloudProductDetailsData getProductPurchasePoints(ProductData productData);

	/**
	 * Gets the annex cloud reward list.
	 *
	 * @return the annex cloud reward list
	 */
	List<AnnexRewardData> getAnnexCloudRewardList();


	/**
	 * Gets the annex cloud credentials.
	 *
	 * @return the annex cloud credentials
	 */
	AnnexCloudData getAnnexCloudCredentials();

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
	 * Gets the annex cloud user points details.
	 *
	 * @return the annex cloud user points details
	 */
	AnnexUserPointsData getAnnexCloudUserPointsDetails();

	/**
	 * Creates the user in annex.
	 *
     */
	void createUserInAnnex();

	/**
	 * Gets the annex cloud user active status.
	 *
	 * @return the annex cloud user active status
	 */
	boolean getAnnexCloudUserActiveStatus();


	/**
	 * News letter signup action.
	 *
	 * @return the AC response
	 */

	/**
	 * Redeem coupons.
	 *
	 * @param coupon
	 *           the coupon
	 * @return the AC custom action response
	 */
	public ACCustomActionResponse redeemCoupons(String coupon);

	/**
	 * Gets the annex applied coupon.
	 *
	 * @return the annex applied coupon
	 */
	public String getAnnexAppliedCoupon();

	/**
	 *
	 */
	ACCartCalculationResponse getCartCalculationPoints();

	/**
	 *
	 */
	AnnexCloudData getAnnexCloudCredentialsWithOptions(List<ACSiteDetailsOptions> options);

	public ACUserDetailsResponse getAnnexCloudUserDetails();
}
