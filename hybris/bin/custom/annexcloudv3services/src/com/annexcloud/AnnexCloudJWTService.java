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
package com.annexcloud;

import com.annex.cloud.loyalty.jwt.ACJwtRequest;
import com.annexcloud.model.AnnexCloudModel;


/**
 * The Interface AnnexCloudJWTService.
 */
public interface AnnexCloudJWTService
{
	
	/**
	 * Creates the bearer token.
	 *
	 * @param payload the payload
	 * @return the string
	 */
	public String createBearerToken(final ACJwtRequest payload);

	/**
	 * Creates the bearer token.
	 *
	 * @param payload the payload
	 * @param annexCloudModel the annex cloud model
	 * @return the string
	 */
	public String createBearerToken(Object payload, AnnexCloudModel annexCloudModel);

	/**
	 * Creates the token.
	 *
	 * @param payload the payload
	 * @param annexCloudModel the annex cloud model
	 * @return the string
	 */
	public String createToken(String payload, AnnexCloudModel annexCloudModel);
}
