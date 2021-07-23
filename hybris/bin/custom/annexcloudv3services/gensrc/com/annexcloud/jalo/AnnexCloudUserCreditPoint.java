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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type AnnexCloudUserCreditPoint.
 */
@SLDSafe
@SuppressWarnings({"unused","cast"})
public class AnnexCloudUserCreditPoint extends GenericItem
{
	/** Qualifier of the <code>AnnexCloudUserCreditPoint.rewardId</code> attribute **/
	public static final String REWARDID = "rewardId";
	/** Qualifier of the <code>AnnexCloudUserCreditPoint.deductAmount</code> attribute **/
	public static final String DEDUCTAMOUNT = "deductAmount";
	protected static final Map<String, AttributeMode> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put(REWARDID, AttributeMode.INITIAL);
		tmp.put(DEDUCTAMOUNT, AttributeMode.INITIAL);
		DEFAULT_INITIAL_ATTRIBUTES = Collections.unmodifiableMap(tmp);
	}
	@Override
	protected Map<String, AttributeMode> getDefaultAttributeModes()
	{
		return DEFAULT_INITIAL_ATTRIBUTES;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudUserCreditPoint.deductAmount</code> attribute.
	 * @return the deductAmount
	 */
	public Double getDeductAmount(final SessionContext ctx)
	{
		return (Double)getProperty( ctx, "deductAmount".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudUserCreditPoint.deductAmount</code> attribute.
	 * @return the deductAmount
	 */
	public Double getDeductAmount()
	{
		return getDeductAmount( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudUserCreditPoint.deductAmount</code> attribute. 
	 * @return the deductAmount
	 */
	public double getDeductAmountAsPrimitive(final SessionContext ctx)
	{
		Double value = getDeductAmount( ctx );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudUserCreditPoint.deductAmount</code> attribute. 
	 * @return the deductAmount
	 */
	public double getDeductAmountAsPrimitive()
	{
		return getDeductAmountAsPrimitive( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudUserCreditPoint.deductAmount</code> attribute. 
	 * @param value the deductAmount
	 */
	public void setDeductAmount(final SessionContext ctx, final Double value)
	{
		setProperty(ctx, "deductAmount".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudUserCreditPoint.deductAmount</code> attribute. 
	 * @param value the deductAmount
	 */
	public void setDeductAmount(final Double value)
	{
		setDeductAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudUserCreditPoint.deductAmount</code> attribute. 
	 * @param value the deductAmount
	 */
	public void setDeductAmount(final SessionContext ctx, final double value)
	{
		setDeductAmount( ctx,Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudUserCreditPoint.deductAmount</code> attribute. 
	 * @param value the deductAmount
	 */
	public void setDeductAmount(final double value)
	{
		setDeductAmount( getSession().getSessionContext(), value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudUserCreditPoint.rewardId</code> attribute.
	 * @return the rewardId
	 */
	public String getRewardId(final SessionContext ctx)
	{
		return (String)getProperty( ctx, "rewardId".intern());
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AnnexCloudUserCreditPoint.rewardId</code> attribute.
	 * @return the rewardId
	 */
	public String getRewardId()
	{
		return getRewardId( getSession().getSessionContext() );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudUserCreditPoint.rewardId</code> attribute. 
	 * @param value the rewardId
	 */
	public void setRewardId(final SessionContext ctx, final String value)
	{
		setProperty(ctx, "rewardId".intern(),value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AnnexCloudUserCreditPoint.rewardId</code> attribute. 
	 * @param value the rewardId
	 */
	public void setRewardId(final String value)
	{
		setRewardId( getSession().getSessionContext(), value );
	}
	
}
