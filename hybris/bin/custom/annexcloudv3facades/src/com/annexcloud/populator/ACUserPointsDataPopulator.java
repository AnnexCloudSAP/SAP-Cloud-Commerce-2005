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
package com.annexcloud.populator;

import de.hybris.platform.commercefacades.product.PriceDataFactory;
import de.hybris.platform.commercefacades.product.data.PriceDataType;
import de.hybris.platform.commercefacades.storesession.StoreSessionFacade;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.math.BigDecimal;

import javax.annotation.Resource;

import com.annex.cloud.data.reward.AnnexUserPointsData;
import com.annex.cloud.v3.api.response.user.ACUserPointsResponse;


/**
 * The Class ACUserPointsDataPopulator.
 */
public class ACUserPointsDataPopulator implements Populator<ACUserPointsResponse, AnnexUserPointsData>
{
	/** The session service. */
	@Resource(name = "cartService")
	private CartService cartService;

	/** The price data factory. */
	@Resource(name = "priceDataFactory")
	PriceDataFactory priceDataFactory;

	/** The store session facade. */
	@Resource(name = "storeSessionFacade")
	private StoreSessionFacade storeSessionFacade;

	/**
	 * Populate.
	 *
	 * @param source
	 *           the source
	 * @param target
	 *           the target
	 * @throws ConversionException
	 *            the conversion exception
	 */
	@Override
	public void populate(final ACUserPointsResponse source, final AnnexUserPointsData target) throws ConversionException
	{
		final CartModel cartModel = cartService.getSessionCart();
		final Double minValue = Math.min(source.getCreditsToCurrencyValue(), cartModel.getSubtotal());
		target.setMaxApplicableCreditOnCart(minValue / Double.parseDouble(source.getCreditsToCurrencyRatio()));
		target.setCreditsToCurrencyValue(priceDataFactory.create(PriceDataType.BUY,
				new BigDecimal(source.getCreditsToCurrencyValue()), storeSessionFacade.getCurrentCurrency().getIsocode()));
		target.setCreditsToCurrencyRatio(source.getCreditsToCurrencyRatio());
		target.setAvailablePoints(source.getAvailablePoints());
		target.setPointsToExpireDate(source.getPointsToExpireDate());
		if (cartModel.getAnnexCloudUserCreditPoint() != null)
		{
			target.setAppliedPointsOnCart(cartModel.getAnnexCloudUserCreditPoint().getDeductAmount()
					/ Double.parseDouble(source.getCreditsToCurrencyRatio()));
		}
	}
}
