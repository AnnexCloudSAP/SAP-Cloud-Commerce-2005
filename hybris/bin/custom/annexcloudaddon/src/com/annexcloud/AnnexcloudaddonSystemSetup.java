package com.annexcloud;

import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.core.Registry;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;

import java.util.ArrayList;
import java.util.List;

import com.annexcloud.constants.AnnexcloudaddonConstants;


@SystemSetup(extension = AnnexcloudaddonConstants.EXTENSIONNAME)
public class AnnexcloudaddonSystemSetup extends AbstractSystemSetup
{


	/**
	 * Creates the essential data.
	 *
	 * @param context
	 *           the context
	 */
	@SystemSetup(type = SystemSetup.Type.ALL, process = SystemSetup.Process.ALL)
	public void createEssentialData(final SystemSetupContext context)
	{
		importImpexFile(context, "/annexcloudaddon/import/contentCatalogs/annexCloudContentCatalog/annex-cms-content.impex");
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
