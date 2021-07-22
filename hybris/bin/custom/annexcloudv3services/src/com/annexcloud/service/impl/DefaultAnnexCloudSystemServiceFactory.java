package com.annexcloud.service.impl;

import com.annexcloud.exceptions.SystemServiceNotSupportedException;
import com.annexcloud.service.AnnexCloudRestIntegrationService;
import com.annexcloud.service.AnnexCloudSystemServiceFactory;
import org.springframework.beans.factory.annotation.Required;

import java.util.Map;


/**
 * Annex Cloud service factory
 */
public class DefaultAnnexCloudSystemServiceFactory implements AnnexCloudSystemServiceFactory
{

	private Map<String, AnnexCloudRestIntegrationService> systemServices;

	/**
	 * get annex cloud service
	 * 
	 * @param integrationCode
	 * @param <T>
	 * @return
	 * @throws SystemServiceNotSupportedException
	 */
	@Override
	public <T extends AnnexCloudRestIntegrationService> T getSystemService(final String integrationCode)
			throws SystemServiceNotSupportedException
	{

		final AnnexCloudRestIntegrationService systemService = this.systemServices.get(integrationCode);
		if (systemService == null)
		{
			throw new SystemServiceNotSupportedException("SystemService not implemented: " + integrationCode);
		}
		else
		{
			return (T) systemService;
		}

	}

	/**
	 * Sets system services.
	 *
	 * @param systemServices
	 *           the system services
	 */
	@Required
	public void setSystemServices(final Map<String, AnnexCloudRestIntegrationService> systemServices)
	{
		this.systemServices = systemServices;
	}
}
