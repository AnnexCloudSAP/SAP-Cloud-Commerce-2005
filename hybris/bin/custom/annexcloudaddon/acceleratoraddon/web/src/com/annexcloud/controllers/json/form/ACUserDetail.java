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
package com.annexcloud.controllers.json.form;

import java.io.Serializable;



/**
 * The Class ACUserDetail.
 */
public class ACUserDetail implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new AC user detail.
	 */
	public ACUserDetail()
	{
		super();
	}

	/** The uid. */
	private String uid;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public String getUid()
	{
		return uid;
	}

	/**
	 * Sets the uid.
	 *
	 * @param uid
	 *           the new uid
	 */
	public void setUid(final String uid)
	{
		this.uid = uid;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *           the new first name
	 */
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *           the new last name
	 */
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}
}
