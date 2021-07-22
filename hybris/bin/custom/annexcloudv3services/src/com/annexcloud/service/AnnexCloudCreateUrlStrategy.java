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
package com.annexcloud.service;

import com.annex.cloud.loyalty.ACRequestDetails;


/**
 * The Interface AnnexCloudCreateUrlStrategy.
 */
public interface AnnexCloudCreateUrlStrategy
{

	/**
	 * Process url.
	 *
	 * @param requestObject
	 *           the request object
	 * @param apiName
	 *           the api name
	 * @return the string
	 */
	public String processUrl(final ACRequestDetails requestObject, String apiName);
}