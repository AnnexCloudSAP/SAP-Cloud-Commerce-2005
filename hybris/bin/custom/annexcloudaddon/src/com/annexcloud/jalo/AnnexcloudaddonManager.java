package com.annexcloud.jalo;

import com.annexcloud.constants.AnnexcloudaddonConstants;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import org.apache.log4j.Logger;

public class AnnexcloudaddonManager extends GeneratedAnnexcloudaddonManager
{
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger( AnnexcloudaddonManager.class.getName() );
	
	public static final AnnexcloudaddonManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (AnnexcloudaddonManager) em.getExtension(AnnexcloudaddonConstants.EXTENSIONNAME);
	}
	
}
