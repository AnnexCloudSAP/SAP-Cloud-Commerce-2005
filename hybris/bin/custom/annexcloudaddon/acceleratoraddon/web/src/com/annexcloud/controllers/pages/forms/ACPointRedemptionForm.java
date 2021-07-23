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

import javax.validation.constraints.Pattern;


// TODO: Auto-generated Javadoc
/**
 * The Class ACPointRedemptionForm.
 */
public class ACPointRedemptionForm
{
	
	/** The deduct amount. */
	String deductAmount;

	/**
	 * Gets the deduct amount.
	 *
	 * @return the deduct amount
	 */
	@Pattern(regexp = "(^$|^?\\d*$)", message = "{annex.point.invalid}")
	public String getDeductAmount()
	{
		return deductAmount;
	}

	/**
	 * Sets the deduct amount.
	 *
	 * @param deductAmount the new deduct amount
	 */
	public void setDeductAmount(final String deductAmount)
	{
		this.deductAmount = deductAmount;
	}


}
