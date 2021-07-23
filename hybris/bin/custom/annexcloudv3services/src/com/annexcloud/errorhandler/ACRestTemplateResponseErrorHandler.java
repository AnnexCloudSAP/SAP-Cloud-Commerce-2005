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
package com.annexcloud.errorhandler;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;


/**
 * The Class ACRestTemplateResponseErrorHandler.
 */
public class ACRestTemplateResponseErrorHandler implements ResponseErrorHandler
{

	/**
	 * Checks for error.
	 *
	 * @param httpResponse
	 *           the http response
	 * @return true, if successful
	 * @throws IOException
	 *            Signals that an I/O exception has occurred.
	 */
	@Override
	public boolean hasError(final ClientHttpResponse httpResponse) throws IOException
	{
		return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	/**
	 * Handle error.
	 *
	 * @param httpResponse
	 *           the http response
	 * @throws IOException
	 *            Signals that an I/O exception has occurred.
	 */
	@Override
	public void handleError(final ClientHttpResponse httpResponse) throws IOException
	{
		if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR)
		{
			//Handle SERVER_ERROR
			throw new HttpClientErrorException(httpResponse.getStatusCode());
		}
		else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
		{
			//Handle CLIENT_ERROR
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND)
			{
				//throw new NotFoundException();

			}
		}
	}
}