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
package com.annexcloud.controllers.pages.forms.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.annexcloud.controllers.pages.forms.ACPointRedemptionForm;


/**
 * The Class PointRedemptionValidator.
 */
@Component("pointRedemptionValidator")
public class PointRedemptionValidator implements Validator
{

	/**
	 * Supports.
	 *
	 * @param aClass
	 *           the a class
	 * @return true, if successful
	 */
	@Override
	public boolean supports(final Class<?> aClass)
	{
		return ACPointRedemptionForm.class.equals(aClass);
	}

	/**
	 * Validate.
	 *
	 * @param object
	 *           the object
	 * @param errors
	 *           the errors
	 */
	@Override
	public void validate(final Object object, final Errors errors)
	{
		final ACPointRedemptionForm form = (ACPointRedemptionForm) object;

		final boolean numeric = form.getDeductAmount().matches("-?\\d+(\\.\\d+)?");
		if (!numeric)
		{
			errors.rejectValue("deductAmount", "annex.point.invalid");
		}
	}
}
