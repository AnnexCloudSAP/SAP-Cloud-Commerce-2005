/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22-Jul-2021, 2:28:03 PM                     ---
 * ----------------------------------------------------------------
 */
package com.annexcloud.jalo;

import com.annexcloud.constants.AnnexcloudaddonConstants;
import com.annexcloud.jalo.components.AnnexCloudImageSliderHomePageComponent;
import de.hybris.platform.acceleratorcms.jalo.components.JspIncludeComponent;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>AnnexcloudaddonManager</code>.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedAnnexcloudaddonManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("actionName", AttributeMode.INITIAL);
		tmp.put("pageId", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.acceleratorcms.jalo.components.JspIncludeComponent", Collections.unmodifiableMap(tmp));
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
	
	/**
	 * <i>Generated method</i> - Getter of the <code>JspIncludeComponent.actionName</code> attribute.
	 * @return the actionName - Annex cloud custom action name
	 */
	public String getActionName(final SessionContext ctx, final JspIncludeComponent item)
	{
		return (String)item.getProperty( ctx, AnnexcloudaddonConstants.Attributes.JspIncludeComponent.ACTIONNAME);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>JspIncludeComponent.actionName</code> attribute.
	 * @return the actionName - Annex cloud custom action name
	 */
	public String getActionName(final JspIncludeComponent item)
	{
		return getActionName( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>JspIncludeComponent.actionName</code> attribute. 
	 * @param value the actionName - Annex cloud custom action name
	 */
	public void setActionName(final SessionContext ctx, final JspIncludeComponent item, final String value)
	{
		item.setProperty(ctx, AnnexcloudaddonConstants.Attributes.JspIncludeComponent.ACTIONNAME,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>JspIncludeComponent.actionName</code> attribute. 
	 * @param value the actionName - Annex cloud custom action name
	 */
	public void setActionName(final JspIncludeComponent item, final String value)
	{
		setActionName( getSession().getSessionContext(), item, value );
	}
	
	public AnnexCloudImageSliderHomePageComponent createAnnexCloudImageSliderHomePageComponent(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType( AnnexcloudaddonConstants.TC.ANNEXCLOUDIMAGESLIDERHOMEPAGECOMPONENT );
			return (AnnexCloudImageSliderHomePageComponent)type.newInstance( ctx, attributeValues );
		}
		catch( JaloGenericCreationException e)
		{
			final Throwable cause = e.getCause();
			throw (cause instanceof RuntimeException ?
			(RuntimeException)cause
			:
			new JaloSystemException( cause, cause.getMessage(), e.getErrorCode() ) );
		}
		catch( JaloBusinessException e )
		{
			throw new JaloSystemException( e ,"error creating AnnexCloudImageSliderHomePageComponent : "+e.getMessage(), 0 );
		}
	}
	
	public AnnexCloudImageSliderHomePageComponent createAnnexCloudImageSliderHomePageComponent(final Map attributeValues)
	{
		return createAnnexCloudImageSliderHomePageComponent( getSession().getSessionContext(), attributeValues );
	}
	
	@Override
	public String getName()
	{
		return AnnexcloudaddonConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>JspIncludeComponent.pageId</code> attribute.
	 * @return the pageId - Annex cloud custom pageId
	 */
	public Integer getPageId(final SessionContext ctx, final JspIncludeComponent item)
	{
		return (Integer)item.getProperty( ctx, AnnexcloudaddonConstants.Attributes.JspIncludeComponent.PAGEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>JspIncludeComponent.pageId</code> attribute.
	 * @return the pageId - Annex cloud custom pageId
	 */
	public Integer getPageId(final JspIncludeComponent item)
	{
		return getPageId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>JspIncludeComponent.pageId</code> attribute. 
	 * @return the pageId - Annex cloud custom pageId
	 */
	public int getPageIdAsPrimitive(final SessionContext ctx, final JspIncludeComponent item)
	{
		Integer value = getPageId( ctx,item );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>JspIncludeComponent.pageId</code> attribute. 
	 * @return the pageId - Annex cloud custom pageId
	 */
	public int getPageIdAsPrimitive(final JspIncludeComponent item)
	{
		return getPageIdAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>JspIncludeComponent.pageId</code> attribute. 
	 * @param value the pageId - Annex cloud custom pageId
	 */
	public void setPageId(final SessionContext ctx, final JspIncludeComponent item, final Integer value)
	{
		item.setProperty(ctx, AnnexcloudaddonConstants.Attributes.JspIncludeComponent.PAGEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>JspIncludeComponent.pageId</code> attribute. 
	 * @param value the pageId - Annex cloud custom pageId
	 */
	public void setPageId(final JspIncludeComponent item, final Integer value)
	{
		setPageId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>JspIncludeComponent.pageId</code> attribute. 
	 * @param value the pageId - Annex cloud custom pageId
	 */
	public void setPageId(final SessionContext ctx, final JspIncludeComponent item, final int value)
	{
		setPageId( ctx, item, Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>JspIncludeComponent.pageId</code> attribute. 
	 * @param value the pageId - Annex cloud custom pageId
	 */
	public void setPageId(final JspIncludeComponent item, final int value)
	{
		setPageId( getSession().getSessionContext(), item, value );
	}
	
}
