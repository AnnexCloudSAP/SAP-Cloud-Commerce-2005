/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.annexcloud.setup;

import static com.annexcloud.constants.Annexcloudv3fulfilmentprocessConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.annexcloud.constants.Annexcloudv3fulfilmentprocessConstants;
import com.annexcloud.service.Annexcloudv3fulfilmentprocessService;


@SystemSetup(extension = Annexcloudv3fulfilmentprocessConstants.EXTENSIONNAME)
public class Annexcloudv3fulfilmentprocessSystemSetup
{
	private final Annexcloudv3fulfilmentprocessService annexcloudv3fulfilmentprocessService;

	public Annexcloudv3fulfilmentprocessSystemSetup(final Annexcloudv3fulfilmentprocessService annexcloudv3fulfilmentprocessService)
	{
		this.annexcloudv3fulfilmentprocessService = annexcloudv3fulfilmentprocessService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		annexcloudv3fulfilmentprocessService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return Annexcloudv3fulfilmentprocessSystemSetup.class.getResourceAsStream("/annexcloudv3fulfilmentprocess/sap-hybris-platform.png");
	}
}
