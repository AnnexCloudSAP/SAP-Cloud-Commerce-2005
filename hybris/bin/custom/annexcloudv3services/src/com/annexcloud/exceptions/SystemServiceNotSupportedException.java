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
package com.annexcloud.exceptions;

/**
 * The Class SystemServiceNotSupportedException.
 */
public class SystemServiceNotSupportedException extends RuntimeException
{


	/**
	 * Instantiates a new system service not supported exception.
	 */
	public SystemServiceNotSupportedException()
	{
		//empty constructor
	}

	/**
	 * Instantiates a new system service not supported exception.
	 *
	 * @param message
	 *           the message
	 * @param exception
	 *           the exception
	 */
	public SystemServiceNotSupportedException(final String message, final Throwable exception)
	{
		super(message, exception);
	}

	/**
	 * Instantiates a new system service not supported exception.
	 *
	 * @param message
	 *           the message
	 */
	public SystemServiceNotSupportedException(final String message)
	{
		super(message);
	}

	/**
	 * Instantiates a new system service not supported exception.
	 *
	 * @param exception
	 *           the exception
	 */
	public SystemServiceNotSupportedException(final Throwable exception)
	{
		super(exception);
	}

}
