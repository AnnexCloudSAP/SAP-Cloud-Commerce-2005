/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.annexcloud.constants;

/**
 * Global class for all Annexcloudaddon web constants. You can add global constants for your extension into this class.
 */
public final class AnnexcloudaddonWebConstants // NOSONAR
{
	private AnnexcloudaddonWebConstants()
	{
		//empty to avoid instantiating this constant class
	}


	// implement here constants used by this extension

	/**
	 * The constant INVALID_EMAIL_ID.
	 */
	public static final String INVALID_EMAIL_ID = "Entered Email Id is Invalid";
	public static final String SIGN_UP = "Signup successfull";
	public static final String ERROR_CREATING = "Error occurred during signup";
	public static final String ANNEX_REGISTRATION_SUCCESSFULLY = "annex.loyalty.registration.successful";
	public static final String ANNEX_REGISTRATION_ERROR = "annex.loyalty.registration.error";
	public static final String ANNEX_REGISTRATION_CONNECTION_ERROR = "annex.loyalty.registration.connection.error";

	/**
	 * The constant ADDON_PREFIX.
	 */
	public static final String ADDON_PREFIX = "addon:/annexcloudaddon/";
}
