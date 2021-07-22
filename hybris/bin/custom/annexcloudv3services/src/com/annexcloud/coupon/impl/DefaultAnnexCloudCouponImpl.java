/**

********  ***     **   ***     **  ******** *       *
********  ****    **   ****    **  ********  *     *
**   **  ** *   **   ** *   **  **         *   **
**   **  ** *   **   ** *   **  ********    * **
********  **  *  **   **  *  **  ********     **
**   **  **   * **   **   * **  **         * **
**   **  **     **   **     **  ********  *   **
**   **  **     ***   **     ***  ******** *     **


 *******   **          ******    **   **   *******
********   **         ********   **   **   ********
**         **         **   **   **   **   **   **
**         **         **   **   **   **   **   **
**         **         **   **   **   **   **   **
**         **         **   **   **   **   **   **
********   ********   ********   ********   *********
 *******   ********    ******     ******    ********


 Annex cloud Copyright (c) 2019
 All software and accompanying documents that you download from Annex Cloud
 are the copyrighted work of Annex Cloud and/or its suppliers. Your use of
 the Software is governed by the terms of the software license agreement
 applicable to the Software ("License Agreement"). You are not authorized to
 install or use any Software unless you first agree to the License Agreement
 terms. All rights, title, and interest to the Software not expressly granted
 are reserved.*/


package com.annexcloud.coupon.impl;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.annexcloud.coupon.ACCouponService;



/**
 * The Class DefaultAnnexCloudCouponImpl.
 */

public class DefaultAnnexCloudCouponImpl implements ACCouponService
{

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(DefaultAnnexCloudCouponImpl.class);

	/** The model service. */
	ModelService modelService;

	/** The cart service. */
	private CartService cartService;



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

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	/**
	 * Gets the annex applied coupon.
	 *
	 * @return the annex applied coupon
	 */
	public String getAnnexAppliedCoupon()
	{
		final CartModel cart = getCartService().getSessionCart();
		if (StringUtils.isNotBlank(cart.getAppliedAnnexCloudCouponCode()))
		{
			return cart.getAppliedAnnexCloudCouponCode();
		}
		else
		{
			return StringUtils.EMPTY;
		}

	}

	/**
	 * Save annex coupon.
	 *
	 * @param coupon
	 *           the coupon
	 * @return true, if successful
	 */
	public boolean saveAnnexCoupon(final String coupon)
	{
		try
		{
			final CartModel cart = getCartService().getSessionCart();
			cart.setAppliedAnnexCloudCouponCode(coupon);
			getModelService().save(cart);
			getModelService().refresh(cart);
		}
		catch (final Exception e)
		{
			return false;
		}
		return true;
	}

	/**
	 * Gets the cart service.
	 *
	 * @return the cart service
	 */
	public CartService getCartService()
	{
		return cartService;
	}

	/**
	 * Sets the cart service.
	 *
	 * @param cartService
	 *           the new cart service
	 */
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}


}
