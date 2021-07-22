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

import bsh.StringUtil;
import de.hybris.platform.commercefacades.order.converters.populator.AbstractOrderPopulator;
import de.hybris.platform.commercefacades.order.data.AbstractOrderData;
import de.hybris.platform.commercefacades.product.data.PriceData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.apache.commons.lang.StringUtils;


/**
 * The Class ACAbstractOrderPopulator.
 *
 * @param <SOURCE>
 *           the generic type
 * @param <TARGET>
 *           the generic type
 */
public class ACAbstractOrderPopulator<SOURCE extends AbstractOrderModel, TARGET extends AbstractOrderData>
		extends AbstractOrderPopulator<SOURCE, TARGET> implements Populator<SOURCE, TARGET>
{


	@Override
	public void populate(final AbstractOrderModel SOURCE, final AbstractOrderData TARGET) throws ConversionException
	{
		if (SOURCE == null)
		{
			TARGET.setTotalPrice(createZeroPrice());
		}
		else
		{
			addTotals(SOURCE, TARGET);
		}
	}

	/**
	 * Adds the totals.
	 *
	 * @param source
	 *           the source
	 * @param prototype
	 *           the prototype
	 */
	@Override
	protected void addTotals(final AbstractOrderModel source, final AbstractOrderData prototype)
	{
		final double orderDiscountsAmount = getOrderDiscountsAmount(source);
		final double quoteDiscountsAmount = getQuoteDiscountsAmount(source);
		prototype.setTotalPrice(createPrice(source, source.getTotalPrice()));
		prototype.setTotalTax(createPrice(source, source.getTotalTax()));
		final double subTotal = source.getSubtotal().doubleValue() - orderDiscountsAmount - quoteDiscountsAmount;
		final PriceData subTotalPriceData = createPrice(source, Double.valueOf(subTotal));
		prototype.setSubTotal(subTotalPriceData);
		prototype.setSubTotalWithoutQuoteDiscounts(createPrice(source, Double.valueOf(subTotal + quoteDiscountsAmount)));
		prototype.setDeliveryCost(source.getDeliveryMode() != null ? createPrice(source, source.getDeliveryCost()) : null);
		prototype.setTotalPriceWithTax((createPrice(source, calcTotalWithTax(source))));
		if (source.getAnnexCloudUserCreditPoint() != null && source.getAnnexCloudUserCreditPoint().getDeductAmount() != null)
		{
			prototype.setAnnexLoyaltyCredit(createPrice(source, source.getAnnexCloudUserCreditPoint().getDeductAmount()));
		}
		if(StringUtils.isNotBlank(source.getAppliedAnnexCloudCouponCode()))
		{
			prototype.setAnnexCouponCode(source.getAppliedAnnexCloudCouponCode());
		}
	}

}
