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

import com.annexcloud.exceptions.SystemServiceNotSupportedException;


/**
 * A factory for creating AnnexCloudSystemService objects.
 */
public interface AnnexCloudSystemServiceFactory
{
	
	/**
	 * Gets the system service.
	 *
	 * @param <T> the generic type
	 * @param integrationCode the integration code
	 * @return the system service
	 * @throws SystemServiceNotSupportedException the system service not supported exception
	 */
	<T extends AnnexCloudRestIntegrationService> T getSystemService(String integrationCode)
			throws SystemServiceNotSupportedException;
}
