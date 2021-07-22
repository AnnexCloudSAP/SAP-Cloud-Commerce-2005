package com.annexcloud.jalo;

import com.annexcloud.constants.AnnexcloudbackofficeConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

public class AnnexcloudbackofficeManager extends GeneratedAnnexcloudbackofficeManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( AnnexcloudbackofficeManager.class.getName() );
	
	public static final AnnexcloudbackofficeManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AnnexcloudbackofficeManager) em.getExtension(AnnexcloudbackofficeConstants.EXTENSIONNAME);
	}
	
}
