/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22-Jul-2021, 2:28:03 PM                     ---
 * ----------------------------------------------------------------
 */
package com.annexcloud.jalo.components;

import com.annexcloud.constants.AnnexcloudaddonConstants;
import de.hybris.platform.cms2.jalo.contents.components.SimpleCMSComponent;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type {@link com.annexcloud.jalo.components.AnnexCloudImageSliderHomePageComponent AnnexCloudImageSliderHomePageComponent}.
 */
@SuppressWarnings({"deprecation","unused","cast"})
public abstract class GeneratedAnnexCloudImageSliderHomePageComponent extends SimpleCMSComponent
{
	/** Qualifier of the <code>AnnexCloudImageSliderHomePageComponent.pageId</code> attribute **/
	public static final String PAGEID = "pageId";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>(SimpleCMSComponent.DEFAULT_INITIAL_ATTRIBUTES);
		tmp.put(PAGEID, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudImageSliderHomePageComponent.pageId</code> attribute.
	 * @return the pageId - Annex cloud custom pageId
	 */
	public Integer getPageId(final SessionContext ctx)
	{
		return (Integer)getProperty( ctx, PAGEID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudImageSliderHomePageComponent.pageId</code> attribute.
	 * @return the pageId - Annex cloud custom pageId
	 */
	public Integer getPageId()
	{
		return getPageId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudImageSliderHomePageComponent.pageId</code> attribute. 
	 * @return the pageId - Annex cloud custom pageId
	 */
	public int getPageIdAsPrimitive(final SessionContext ctx)
	{
		Integer value = getPageId( ctx );
		return value != null ? value.intValue() : 0;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudImageSliderHomePageComponent.pageId</code> attribute. 
	 * @return the pageId - Annex cloud custom pageId
	 */
	public int getPageIdAsPrimitive()
	{
		return getPageIdAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudImageSliderHomePageComponent.pageId</code> attribute. 
	 * @param value the pageId - Annex cloud custom pageId
	 */
	public void setPageId(final SessionContext ctx, final Integer value)
	{
		setProperty(ctx, PAGEID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudImageSliderHomePageComponent.pageId</code> attribute. 
	 * @param value the pageId - Annex cloud custom pageId
	 */
	public void setPageId(final Integer value)
	{
		setPageId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudImageSliderHomePageComponent.pageId</code> attribute. 
	 * @param value the pageId - Annex cloud custom pageId
	 */
	public void setPageId(final SessionContext ctx, final int value)
	{
		setPageId( ctx,Integer.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudImageSliderHomePageComponent.pageId</code> attribute. 
	 * @param value the pageId - Annex cloud custom pageId
	 */
	public void setPageId(final int value)
	{
		setPageId( getSession().getSessionContext(), value );
	}
	
}
