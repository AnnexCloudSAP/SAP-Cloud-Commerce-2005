/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22-Jul-2021, 2:28:03 PM                     ---
 * ----------------------------------------------------------------
 */
package com.annexcloud.jalo;

import de.hybris.platform.directpersistence.annotation.SLDSafe;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.enumeration.EnumerationValue;
import de.hybris.platform.store.BaseStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type AnnexCloud.
 */
@SLDSafe
@SuppressWarnings({"unused","cast"})
public class AnnexCloud extends GenericItem
{
	/** Qualifier of the <code>AnnexCloud.code</code> attribute **/
	public static final String CODE = "code";
	/** Qualifier of the <code>AnnexCloud.siteId</code> attribute **/
	public static final String SITEID = "siteId";
	/** Qualifier of the <code>AnnexCloud.templateId</code> attribute **/
	public static final String TEMPLATEID = "templateId";
	/** Qualifier of the <code>AnnexCloud.baseStore</code> attribute **/
	public static final String BASESTORE = "baseStore";
	/** Qualifier of the <code>AnnexCloud.loyaltyProgramType</code> attribute **/
	public static final String LOYALTYPROGRAMTYPE = "loyaltyProgramType";
	/** Qualifier of the <code>AnnexCloud.pointRedemptionType</code> attribute **/
	public static final String POINTREDEMPTIONTYPE = "pointRedemptionType";
	/** Qualifier of the <code>AnnexCloud.acPkType</code> attribute **/
	public static final String ACPKTYPE = "acPkType";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(CODE, AttributeMode.INITIAL);
		tmp.put(SITEID, AttributeMode.INITIAL);
		tmp.put(TEMPLATEID, AttributeMode.INITIAL);
		tmp.put(BASESTORE, AttributeMode.INITIAL);
		tmp.put(LOYALTYPROGRAMTYPE, AttributeMode.INITIAL);
		tmp.put(POINTREDEMPTIONTYPE, AttributeMode.INITIAL);
		tmp.put(ACPKTYPE, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.acPkType</code> attribute.
	 * @return the acPkType
	 */
	public EnumerationValue getAcPkType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, "acPkType".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.acPkType</code> attribute.
	 * @return the acPkType
	 */
	public EnumerationValue getAcPkType()
	{
		return getAcPkType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.acPkType</code> attribute. 
	 * @param value the acPkType
	 */
	public void setAcPkType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, "acPkType".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.acPkType</code> attribute. 
	 * @param value the acPkType
	 */
	public void setAcPkType(final EnumerationValue value)
	{
		setAcPkType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.baseStore</code> attribute.
	 * @return the baseStore
	 */
	public BaseStore getBaseStore(final SessionContext ctx)
	{
		return (BaseStore)getProperty( ctx, "baseStore".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.baseStore</code> attribute.
	 * @return the baseStore
	 */
	public BaseStore getBaseStore()
	{
		return getBaseStore( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.baseStore</code> attribute. 
	 * @param value the baseStore
	 */
	public void setBaseStore(final SessionContext ctx, final BaseStore value)
	{
		setProperty(ctx, "baseStore".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.baseStore</code> attribute. 
	 * @param value the baseStore
	 */
	public void setBaseStore(final BaseStore value)
	{
		setBaseStore( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.code</code> attribute.
	 * @return the code
	 */
	public String getCode(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "code".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.code</code> attribute.
	 * @return the code
	 */
	public String getCode()
	{
		return getCode( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "code".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.code</code> attribute. 
	 * @param value the code
	 */
	public void setCode(final String value)
	{
		setCode( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.loyaltyProgramType</code> attribute.
	 * @return the loyaltyProgramType
	 */
	public EnumerationValue getLoyaltyProgramType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, "loyaltyProgramType".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.loyaltyProgramType</code> attribute.
	 * @return the loyaltyProgramType
	 */
	public EnumerationValue getLoyaltyProgramType()
	{
		return getLoyaltyProgramType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.loyaltyProgramType</code> attribute. 
	 * @param value the loyaltyProgramType
	 */
	public void setLoyaltyProgramType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, "loyaltyProgramType".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.loyaltyProgramType</code> attribute. 
	 * @param value the loyaltyProgramType
	 */
	public void setLoyaltyProgramType(final EnumerationValue value)
	{
		setLoyaltyProgramType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.pointRedemptionType</code> attribute.
	 * @return the pointRedemptionType
	 */
	public EnumerationValue getPointRedemptionType(final SessionContext ctx)
	{
		return (EnumerationValue)getProperty( ctx, "pointRedemptionType".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.pointRedemptionType</code> attribute.
	 * @return the pointRedemptionType
	 */
	public EnumerationValue getPointRedemptionType()
	{
		return getPointRedemptionType( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.pointRedemptionType</code> attribute. 
	 * @param value the pointRedemptionType
	 */
	public void setPointRedemptionType(final SessionContext ctx, final EnumerationValue value)
	{
		setProperty(ctx, "pointRedemptionType".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.pointRedemptionType</code> attribute. 
	 * @param value the pointRedemptionType
	 */
	public void setPointRedemptionType(final EnumerationValue value)
	{
		setPointRedemptionType( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.siteId</code> attribute.
	 * @return the siteId
	 */
	public String getSiteId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "siteId".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.siteId</code> attribute.
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return getSiteId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.siteId</code> attribute. 
	 * @param value the siteId
	 */
	public void setSiteId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "siteId".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.siteId</code> attribute. 
	 * @param value the siteId
	 */
	public void setSiteId(final String value)
	{
		setSiteId( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.templateId</code> attribute.
	 * @return the templateId
	 */
	public String getTemplateId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "templateId".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloud.templateId</code> attribute.
	 * @return the templateId
	 */
	public String getTemplateId()
	{
		return getTemplateId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.templateId</code> attribute. 
	 * @param value the templateId
	 */
	public void setTemplateId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "templateId".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloud.templateId</code> attribute. 
	 * @param value the templateId
	 */
	public void setTemplateId(final String value)
	{
		setTemplateId( getSession().getSessionContext(), value );
	}
	
}
