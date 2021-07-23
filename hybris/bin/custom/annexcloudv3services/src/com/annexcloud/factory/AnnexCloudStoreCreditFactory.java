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
package com.annexcloud.factory;

import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.payment.enums.PaymentTransactionType;
import de.hybris.platform.payment.model.PaymentTransactionEntryModel;
import de.hybris.platform.payment.model.PaymentTransactionModel;

import java.math.BigDecimal;


/**
 * A factory for creating AnnexCloudStoreCredit objects.
 */
public interface AnnexCloudStoreCreditFactory
{

	/**
	 * Creates a new AnnexCloudStoreCredit object.
	 *
	 * @param cartModel
	 *           the cart model
	 * @param amountToApply
	 *           the amount to apply
	 * @return the payment transaction model
	 */
	public PaymentTransactionModel createPaymentTransaction(final CartModel cartModel, final BigDecimal amountToApply);

	/**
	 * Creates a new AnnexCloudStoreCredit object.
	 *
	 * @param cartModel
	 *           the cart model
	 * @param paymentTransactionModel
	 *           the payment transaction model
	 * @param transactionAmount
	 *           the transaction amount
	 * @return the payment transaction entry model
	 */

	public PaymentTransactionEntryModel createPaymentTransactionEntry(final CartModel cartModel,
			final PaymentTransactionModel paymentTransactionModel, final BigDecimal transactionAmount,
			PaymentTransactionType transactionType);


}
