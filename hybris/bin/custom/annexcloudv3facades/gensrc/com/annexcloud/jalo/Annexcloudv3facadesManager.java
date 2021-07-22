/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22-Jul-2021, 2:28:03 PM                     ---
 * ----------------------------------------------------------------
 */
package com.annexcloud.jalo;

import com.annexcloud.constants.Annexcloudv3facadesConstants;
import de.hybris.platform.directpersistence.annotation.SLDSafe;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.extension.ExtensionManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>Annexcloudv3facadesManager</code>.
 */
@SuppressWarnings({"unused","cast"})
@SLDSafe
public class Annexcloudv3facadesManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		DEFAULT_INITIAL_ATTRIBUTES = ttmp;
	}
	@Override
	public Map<String, AttributeMode> getDefaultAttributeModes(final Class<? extends Item> itemClass)
	{
		Map<String, AttributeMode> ret = new HashMap<>();
		final Map<String, AttributeMode> attr = DEFAULT_INITIAL_ATTRIBUTES.get(itemClass.getName());
		if (attr != null)
		{
			ret.putAll(attr);
		}
		return ret;
	}
	
	public static final Annexcloudv3facadesManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (Annexcloudv3facadesManager) em.getExtension(Annexcloudv3facadesConstants.EXTENSIONNAME);
	}
	
	@Override
	public String getName()
	{
		return Annexcloudv3facadesConstants.EXTENSIONNAME;
	}
	
}
