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
package com.annexcloud.controllers.pages.forms;

/**
 * The Class CouponRedemptionForm.
 */
public class CouponRedemptionForm
{

	/** The coupon. */
	String coupon;

	/** The reward id. */
	String rewardId;

	/**
	 * Instantiates a new coupon redemption form.
	 */
	public CouponRedemptionForm()
	{
	}

	/**
	 * Gets the coupon.
	 *
	 * @return the coupon
	 */
	public String getCoupon()
	{
		return coupon;
	}

	/**
	 * Sets the coupon.
	 *
	 * @param coupon
	 *           the new coupon
	 */
	public void setCoupon(final String coupon)
	{
		this.coupon = coupon;
	}

	/**
	 * Gets the reward id.
	 *
	 * @return the reward id
	 */
	public String getRewardId()
	{
		return rewardId;
	}

	/**
	 * Sets the reward id.
	 *
	 * @param rewardId
	 *           the new reward id
	 */
	public void setRewardId(final String rewardId)
	{
		this.rewardId = rewardId;
	}

}
