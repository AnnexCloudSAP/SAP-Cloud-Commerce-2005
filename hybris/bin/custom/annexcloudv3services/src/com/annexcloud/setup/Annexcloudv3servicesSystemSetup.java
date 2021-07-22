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
package com.annexcloud.setup;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetup.Process;
import de.hybris.platform.core.initialization.SystemSetup.Type;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.annexcloud.constants.AnnexCloudV3Constants;


/**
 * The Class Annexcloudv3servicesSystemSetup.
 */
@SystemSetup(extension = AnnexCloudV3Constants.EXTENSIONNAME)
public class Annexcloudv3servicesSystemSetup extends AbstractSystemSetup
{

	private static final Logger LOG = Logger.getLogger(Annexcloudv3servicesSystemSetup.class);

	/**
	 * Creates the essential data.
	 *
	 * @param context
	 *           the context
	 */
	@SystemSetup(type = Type.ESSENTIAL, process = Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		importImpexFile(context, "/impex/annexcloud-cms-content.impex");
		//	importImpexFile(context, "/impex/annexcloud-core.impex");
	}

	/**
	 * Gets the initialization options.
	 *
	 * @return the initialization options
	 */
	@Override
	@SystemSetupParameterMethod
	public List<SystemSetupParameter> getInitializationOptions()
	{
		return new ArrayList();
	}

	/**
	 * Gets the extension names.
	 *
	 * @return the extension names
	 */
	protected List<String> getExtensionNames()
	{
		return Registry.getCurrentTenant().getTenantSpecificExtensionNames();
	}
}
