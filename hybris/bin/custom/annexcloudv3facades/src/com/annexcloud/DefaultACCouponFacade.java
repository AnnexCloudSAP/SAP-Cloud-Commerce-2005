

/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2020 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.annexcloud;

import de.hybris.platform.commercefacades.voucher.exceptions.VoucherOperationException;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.couponfacades.facades.impl.DefaultCouponFacade;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.model.ModelService;

import org.apache.commons.lang.StringUtils;


/**
 * The Class DefaultACCouponFacade.
 */
public class DefaultACCouponFacade extends DefaultCouponFacade
{

	/** The cart service. */
	private CartService cartService;

	/** The model service. */
	ModelService modelService;

	/**
	 * Release voucher.
	 *
	 * @param voucherCode
	 *           the voucher code
	 * @throws VoucherOperationException
	 *            the voucher operation exception
	 */
	@Override
	public void releaseVoucher(final String voucherCode) throws VoucherOperationException
	{
		super.releaseVoucher(voucherCode);
		final CartModel cart = getCartService().getSessionCart();
		if (StringUtils.isNotBlank(cart.getAppliedAnnexCloudCouponCode())
				&& cart.getAppliedAnnexCloudCouponCode().equals(voucherCode))
		{
			cart.setAppliedAnnexCloudCouponCode(StringUtils.EMPTY);

			try
			{
				getModelService().save(cart);
				getModelService().refresh(cart);
			}
			catch (final Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the cart service.
	 *
	 * @return the cart service
	 */
	@Override
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
	@Override
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
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
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}





}
