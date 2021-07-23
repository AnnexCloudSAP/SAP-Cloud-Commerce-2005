/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22-Jul-2021, 2:28:03 PM                     ---
 * ----------------------------------------------------------------
 */
package com.annexcloud.jalo;

import com.annexcloud.constants.Annexcloudv3servicesConstants;
import com.annexcloud.jalo.AnnexCloud;
import com.annexcloud.jalo.AnnexCloudUserCreditPoint;
import de.hybris.platform.category.jalo.Category;
import de.hybris.platform.directpersistence.annotation.SLDSafe;
import de.hybris.platform.jalo.GenericItem;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.Item.AttributeMode;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.JaloSystemException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.extension.Extension;
import de.hybris.platform.jalo.extension.ExtensionManager;
import de.hybris.platform.jalo.order.AbstractOrder;
import de.hybris.platform.jalo.product.Product;
import de.hybris.platform.jalo.type.ComposedType;
import de.hybris.platform.jalo.type.JaloGenericCreationException;
import de.hybris.platform.jalo.user.Customer;
import de.hybris.platform.jalo.user.User;
import de.hybris.platform.personalizationservices.jalo.CxSegment;
import de.hybris.platform.store.BaseStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Generated class for type <code>Annexcloudv3servicesManager</code>.
 */
@SuppressWarnings({"unused","cast"})
@SLDSafe
public class Annexcloudv3servicesManager extends Extension
{
	protected static final Map<String, Map<String, AttributeMode>> DEFAULT_INITIAL_ATTRIBUTES;
	static
	{
		final Map<String, Map<String, AttributeMode>> ttmp = new HashMap();
		Map<String, AttributeMode> tmp = new HashMap<String, AttributeMode>();
		tmp.put("categoryPointType", AttributeMode.INITIAL);
		tmp.put("categoryPointRatio", AttributeMode.INITIAL);
		tmp.put("categoryBonusFlag", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.category.jalo.Category", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("averageRatings", AttributeMode.INITIAL);
		tmp.put("acSynced", AttributeMode.INITIAL);
		tmp.put("productBonusFlag", AttributeMode.INITIAL);
		tmp.put("productPointRatio", AttributeMode.INITIAL);
		tmp.put("productMinimumLimit", AttributeMode.INITIAL);
		tmp.put("productBonus", AttributeMode.INITIAL);
		tmp.put("productPointType", AttributeMode.INITIAL);
		tmp.put("pointAwardType", AttributeMode.INITIAL);
		tmp.put("productPoints", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.product.Product", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("acId", AttributeMode.INITIAL);
		tmp.put("optInStatus", AttributeMode.INITIAL);
		tmp.put("optInDate", AttributeMode.INITIAL);
		tmp.put("availablePoints", AttributeMode.INITIAL);
		tmp.put("usedPoints", AttributeMode.INITIAL);
		tmp.put("lifetimePoints", AttributeMode.INITIAL);
		tmp.put("pointsToExpire", AttributeMode.INITIAL);
		tmp.put("pointsToExpireDate", AttributeMode.INITIAL);
		tmp.put("currentTier", AttributeMode.INITIAL);
		tmp.put("nextTier", AttributeMode.INITIAL);
		tmp.put("pointsToNextTier", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.user.Customer", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("acSegmentId", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.personalizationservices.jalo.CxSegment", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("annexCloud", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.store.BaseStore", Collections.unmodifiableMap(tmp));
		tmp = new HashMap<String, AttributeMode>();
		tmp.put("appliedAnnexCloudCouponCode", AttributeMode.INITIAL);
		tmp.put("annexCloudUserCreditPoint", AttributeMode.INITIAL);
		ttmp.put("de.hybris.platform.jalo.order.AbstractOrder", Collections.unmodifiableMap(tmp));
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
	 * <i>Generated method</i> - Getter of the <code>Customer.acId</code> attribute.
	 * @return the acId
	 */
	public String getAcId(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.ACID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.acId</code> attribute.
	 * @return the acId
	 */
	public String getAcId(final Customer item)
	{
		return getAcId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.acId</code> attribute. 
	 * @param value the acId
	 */
	public void setAcId(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.ACID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.acId</code> attribute. 
	 * @param value the acId
	 */
	public void setAcId(final Customer item, final String value)
	{
		setAcId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxSegment.acSegmentId</code> attribute.
	 * @return the acSegmentId - Annex Cloud segment id
	 */
	public String getAcSegmentId(final SessionContext ctx, final CxSegment item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.CxSegment.ACSEGMENTID);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>CxSegment.acSegmentId</code> attribute.
	 * @return the acSegmentId - Annex Cloud segment id
	 */
	public String getAcSegmentId(final CxSegment item)
	{
		return getAcSegmentId( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxSegment.acSegmentId</code> attribute. 
	 * @param value the acSegmentId - Annex Cloud segment id
	 */
	public void setAcSegmentId(final SessionContext ctx, final CxSegment item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.CxSegment.ACSEGMENTID,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>CxSegment.acSegmentId</code> attribute. 
	 * @param value the acSegmentId - Annex Cloud segment id
	 */
	public void setAcSegmentId(final CxSegment item, final String value)
	{
		setAcSegmentId( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.acSynced</code> attribute.
	 * @return the acSynced
	 */
	public String getAcSynced(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Product.ACSYNCED);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.acSynced</code> attribute.
	 * @return the acSynced
	 */
	public String getAcSynced(final Product item)
	{
		return getAcSynced( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.acSynced</code> attribute. 
	 * @param value the acSynced
	 */
	public void setAcSynced(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Product.ACSYNCED,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.acSynced</code> attribute. 
	 * @param value the acSynced
	 */
	public void setAcSynced(final Product item, final String value)
	{
		setAcSynced( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.annexCloud</code> attribute.
	 * @return the annexCloud
	 */
	public AnnexCloud getAnnexCloud(final SessionContext ctx, final BaseStore item)
	{
		return (AnnexCloud)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.BaseStore.ANNEXCLOUD);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>BaseStore.annexCloud</code> attribute.
	 * @return the annexCloud
	 */
	public AnnexCloud getAnnexCloud(final BaseStore item)
	{
		return getAnnexCloud( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.annexCloud</code> attribute. 
	 * @param value the annexCloud
	 */
	public void setAnnexCloud(final SessionContext ctx, final BaseStore item, final AnnexCloud value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.BaseStore.ANNEXCLOUD,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>BaseStore.annexCloud</code> attribute. 
	 * @param value the annexCloud
	 */
	public void setAnnexCloud(final BaseStore item, final AnnexCloud value)
	{
		setAnnexCloud( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.annexCloudUserCreditPoint</code> attribute.
	 * @return the annexCloudUserCreditPoint
	 */
	public AnnexCloudUserCreditPoint getAnnexCloudUserCreditPoint(final SessionContext ctx, final AbstractOrder item)
	{
		return (AnnexCloudUserCreditPoint)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.AbstractOrder.ANNEXCLOUDUSERCREDITPOINT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.annexCloudUserCreditPoint</code> attribute.
	 * @return the annexCloudUserCreditPoint
	 */
	public AnnexCloudUserCreditPoint getAnnexCloudUserCreditPoint(final AbstractOrder item)
	{
		return getAnnexCloudUserCreditPoint( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.annexCloudUserCreditPoint</code> attribute. 
	 * @param value the annexCloudUserCreditPoint
	 */
	public void setAnnexCloudUserCreditPoint(final SessionContext ctx, final AbstractOrder item, final AnnexCloudUserCreditPoint value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.AbstractOrder.ANNEXCLOUDUSERCREDITPOINT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.annexCloudUserCreditPoint</code> attribute. 
	 * @param value the annexCloudUserCreditPoint
	 */
	public void setAnnexCloudUserCreditPoint(final AbstractOrder item, final AnnexCloudUserCreditPoint value)
	{
		setAnnexCloudUserCreditPoint( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.appliedAnnexCloudCouponCode</code> attribute.
	 * @return the appliedAnnexCloudCouponCode
	 */
	public String getAppliedAnnexCloudCouponCode(final SessionContext ctx, final AbstractOrder item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.AbstractOrder.APPLIEDANNEXCLOUDCOUPONCODE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>AbstractOrder.appliedAnnexCloudCouponCode</code> attribute.
	 * @return the appliedAnnexCloudCouponCode
	 */
	public String getAppliedAnnexCloudCouponCode(final AbstractOrder item)
	{
		return getAppliedAnnexCloudCouponCode( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.appliedAnnexCloudCouponCode</code> attribute. 
	 * @param value the appliedAnnexCloudCouponCode
	 */
	public void setAppliedAnnexCloudCouponCode(final SessionContext ctx, final AbstractOrder item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.AbstractOrder.APPLIEDANNEXCLOUDCOUPONCODE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>AbstractOrder.appliedAnnexCloudCouponCode</code> attribute. 
	 * @param value the appliedAnnexCloudCouponCode
	 */
	public void setAppliedAnnexCloudCouponCode(final AbstractOrder item, final String value)
	{
		setAppliedAnnexCloudCouponCode( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.availablePoints</code> attribute.
	 * @return the availablePoints
	 */
	public String getAvailablePoints(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.AVAILABLEPOINTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.availablePoints</code> attribute.
	 * @return the availablePoints
	 */
	public String getAvailablePoints(final Customer item)
	{
		return getAvailablePoints( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.availablePoints</code> attribute. 
	 * @param value the availablePoints
	 */
	public void setAvailablePoints(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.AVAILABLEPOINTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.availablePoints</code> attribute. 
	 * @param value the availablePoints
	 */
	public void setAvailablePoints(final Customer item, final String value)
	{
		setAvailablePoints( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.averageRatings</code> attribute.
	 * @return the averageRatings
	 */
	public Double getAverageRatings(final SessionContext ctx, final Product item)
	{
		return (Double)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Product.AVERAGERATINGS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.averageRatings</code> attribute.
	 * @return the averageRatings
	 */
	public Double getAverageRatings(final Product item)
	{
		return getAverageRatings( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.averageRatings</code> attribute. 
	 * @return the averageRatings
	 */
	public double getAverageRatingsAsPrimitive(final SessionContext ctx, final Product item)
	{
		Double value = getAverageRatings( ctx,item );
		return value != null ? value.doubleValue() : 0.0d;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.averageRatings</code> attribute. 
	 * @return the averageRatings
	 */
	public double getAverageRatingsAsPrimitive(final Product item)
	{
		return getAverageRatingsAsPrimitive( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.averageRatings</code> attribute. 
	 * @param value the averageRatings
	 */
	public void setAverageRatings(final SessionContext ctx, final Product item, final Double value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Product.AVERAGERATINGS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.averageRatings</code> attribute. 
	 * @param value the averageRatings
	 */
	public void setAverageRatings(final Product item, final Double value)
	{
		setAverageRatings( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.averageRatings</code> attribute. 
	 * @param value the averageRatings
	 */
	public void setAverageRatings(final SessionContext ctx, final Product item, final double value)
	{
		setAverageRatings( ctx, item, Double.valueOf( value ) );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.averageRatings</code> attribute. 
	 * @param value the averageRatings
	 */
	public void setAverageRatings(final Product item, final double value)
	{
		setAverageRatings( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.categoryBonusFlag</code> attribute.
	 * @return the categoryBonusFlag
	 */
	public String getCategoryBonusFlag(final SessionContext ctx, final Category item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Category.CATEGORYBONUSFLAG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.categoryBonusFlag</code> attribute.
	 * @return the categoryBonusFlag
	 */
	public String getCategoryBonusFlag(final Category item)
	{
		return getCategoryBonusFlag( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.categoryBonusFlag</code> attribute. 
	 * @param value the categoryBonusFlag
	 */
	public void setCategoryBonusFlag(final SessionContext ctx, final Category item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Category.CATEGORYBONUSFLAG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.categoryBonusFlag</code> attribute. 
	 * @param value the categoryBonusFlag
	 */
	public void setCategoryBonusFlag(final Category item, final String value)
	{
		setCategoryBonusFlag( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.categoryPointRatio</code> attribute.
	 * @return the categoryPointRatio
	 */
	public String getCategoryPointRatio(final SessionContext ctx, final Category item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Category.CATEGORYPOINTRATIO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.categoryPointRatio</code> attribute.
	 * @return the categoryPointRatio
	 */
	public String getCategoryPointRatio(final Category item)
	{
		return getCategoryPointRatio( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.categoryPointRatio</code> attribute. 
	 * @param value the categoryPointRatio
	 */
	public void setCategoryPointRatio(final SessionContext ctx, final Category item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Category.CATEGORYPOINTRATIO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.categoryPointRatio</code> attribute. 
	 * @param value the categoryPointRatio
	 */
	public void setCategoryPointRatio(final Category item, final String value)
	{
		setCategoryPointRatio( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.categoryPointType</code> attribute.
	 * @return the categoryPointType
	 */
	public String getCategoryPointType(final SessionContext ctx, final Category item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Category.CATEGORYPOINTTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Category.categoryPointType</code> attribute.
	 * @return the categoryPointType
	 */
	public String getCategoryPointType(final Category item)
	{
		return getCategoryPointType( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.categoryPointType</code> attribute. 
	 * @param value the categoryPointType
	 */
	public void setCategoryPointType(final SessionContext ctx, final Category item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Category.CATEGORYPOINTTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Category.categoryPointType</code> attribute. 
	 * @param value the categoryPointType
	 */
	public void setCategoryPointType(final Category item, final String value)
	{
		setCategoryPointType( getSession().getSessionContext(), item, value );
	}
	
	public AnnexCloud createAnnexCloud(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType("AnnexCloud");
			return (AnnexCloud)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating AnnexCloud : "+e.getMessage(), 0 );
		}
	}
	
	public AnnexCloud createAnnexCloud(final Map attributeValues)
	{
		return createAnnexCloud( getSession().getSessionContext(), attributeValues );
	}
	
	public AnnexCloudUserCreditPoint createAnnexCloudUserCreditPoint(final SessionContext ctx, final Map attributeValues)
	{
		try
		{
			ComposedType type = getTenant().getJaloConnection().getTypeManager().getComposedType("AnnexCloudUserCreditPoint");
			return (AnnexCloudUserCreditPoint)type.newInstance( ctx, attributeValues );
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
			throw new JaloSystemException( e ,"error creating AnnexCloudUserCreditPoint : "+e.getMessage(), 0 );
		}
	}
	
	public AnnexCloudUserCreditPoint createAnnexCloudUserCreditPoint(final Map attributeValues)
	{
		return createAnnexCloudUserCreditPoint( getSession().getSessionContext(), attributeValues );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.currentTier</code> attribute.
	 * @return the currentTier
	 */
	public String getCurrentTier(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.CURRENTTIER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.currentTier</code> attribute.
	 * @return the currentTier
	 */
	public String getCurrentTier(final Customer item)
	{
		return getCurrentTier( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.currentTier</code> attribute. 
	 * @param value the currentTier
	 */
	public void setCurrentTier(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.CURRENTTIER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.currentTier</code> attribute. 
	 * @param value the currentTier
	 */
	public void setCurrentTier(final Customer item, final String value)
	{
		setCurrentTier( getSession().getSessionContext(), item, value );
	}
	
	public static final Annexcloudv3servicesManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (Annexcloudv3servicesManager) em.getExtension(Annexcloudv3servicesConstants.EXTENSIONNAME);
	}
	
	@Override
	public String getName()
	{
		return Annexcloudv3servicesConstants.EXTENSIONNAME;
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.lifetimePoints</code> attribute.
	 * @return the lifetimePoints
	 */
	public String getLifetimePoints(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.LIFETIMEPOINTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.lifetimePoints</code> attribute.
	 * @return the lifetimePoints
	 */
	public String getLifetimePoints(final Customer item)
	{
		return getLifetimePoints( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.lifetimePoints</code> attribute. 
	 * @param value the lifetimePoints
	 */
	public void setLifetimePoints(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.LIFETIMEPOINTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.lifetimePoints</code> attribute. 
	 * @param value the lifetimePoints
	 */
	public void setLifetimePoints(final Customer item, final String value)
	{
		setLifetimePoints( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.nextTier</code> attribute.
	 * @return the nextTier
	 */
	public String getNextTier(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.NEXTTIER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.nextTier</code> attribute.
	 * @return the nextTier
	 */
	public String getNextTier(final Customer item)
	{
		return getNextTier( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.nextTier</code> attribute. 
	 * @param value the nextTier
	 */
	public void setNextTier(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.NEXTTIER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.nextTier</code> attribute. 
	 * @param value the nextTier
	 */
	public void setNextTier(final Customer item, final String value)
	{
		setNextTier( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.optInDate</code> attribute.
	 * @return the optInDate
	 */
	public String getOptInDate(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.OPTINDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.optInDate</code> attribute.
	 * @return the optInDate
	 */
	public String getOptInDate(final Customer item)
	{
		return getOptInDate( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.optInDate</code> attribute. 
	 * @param value the optInDate
	 */
	public void setOptInDate(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.OPTINDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.optInDate</code> attribute. 
	 * @param value the optInDate
	 */
	public void setOptInDate(final Customer item, final String value)
	{
		setOptInDate( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.optInStatus</code> attribute.
	 * @return the optInStatus
	 */
	public String getOptInStatus(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.OPTINSTATUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.optInStatus</code> attribute.
	 * @return the optInStatus
	 */
	public String getOptInStatus(final Customer item)
	{
		return getOptInStatus( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.optInStatus</code> attribute. 
	 * @param value the optInStatus
	 */
	public void setOptInStatus(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.OPTINSTATUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.optInStatus</code> attribute. 
	 * @param value the optInStatus
	 */
	public void setOptInStatus(final Customer item, final String value)
	{
		setOptInStatus( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.pointAwardType</code> attribute.
	 * @return the pointAwardType
	 */
	public String getPointAwardType(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Product.POINTAWARDTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.pointAwardType</code> attribute.
	 * @return the pointAwardType
	 */
	public String getPointAwardType(final Product item)
	{
		return getPointAwardType( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.pointAwardType</code> attribute. 
	 * @param value the pointAwardType
	 */
	public void setPointAwardType(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Product.POINTAWARDTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.pointAwardType</code> attribute. 
	 * @param value the pointAwardType
	 */
	public void setPointAwardType(final Product item, final String value)
	{
		setPointAwardType( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.pointsToExpire</code> attribute.
	 * @return the pointsToExpire
	 */
	public String getPointsToExpire(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.POINTSTOEXPIRE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.pointsToExpire</code> attribute.
	 * @return the pointsToExpire
	 */
	public String getPointsToExpire(final Customer item)
	{
		return getPointsToExpire( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.pointsToExpire</code> attribute. 
	 * @param value the pointsToExpire
	 */
	public void setPointsToExpire(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.POINTSTOEXPIRE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.pointsToExpire</code> attribute. 
	 * @param value the pointsToExpire
	 */
	public void setPointsToExpire(final Customer item, final String value)
	{
		setPointsToExpire( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.pointsToExpireDate</code> attribute.
	 * @return the pointsToExpireDate
	 */
	public String getPointsToExpireDate(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.POINTSTOEXPIREDATE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.pointsToExpireDate</code> attribute.
	 * @return the pointsToExpireDate
	 */
	public String getPointsToExpireDate(final Customer item)
	{
		return getPointsToExpireDate( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.pointsToExpireDate</code> attribute. 
	 * @param value the pointsToExpireDate
	 */
	public void setPointsToExpireDate(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.POINTSTOEXPIREDATE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.pointsToExpireDate</code> attribute. 
	 * @param value the pointsToExpireDate
	 */
	public void setPointsToExpireDate(final Customer item, final String value)
	{
		setPointsToExpireDate( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.pointsToNextTier</code> attribute.
	 * @return the pointsToNextTier
	 */
	public String getPointsToNextTier(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.POINTSTONEXTTIER);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.pointsToNextTier</code> attribute.
	 * @return the pointsToNextTier
	 */
	public String getPointsToNextTier(final Customer item)
	{
		return getPointsToNextTier( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.pointsToNextTier</code> attribute. 
	 * @param value the pointsToNextTier
	 */
	public void setPointsToNextTier(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.POINTSTONEXTTIER,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.pointsToNextTier</code> attribute. 
	 * @param value the pointsToNextTier
	 */
	public void setPointsToNextTier(final Customer item, final String value)
	{
		setPointsToNextTier( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productBonus</code> attribute.
	 * @return the productBonus
	 */
	public String getProductBonus(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTBONUS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productBonus</code> attribute.
	 * @return the productBonus
	 */
	public String getProductBonus(final Product item)
	{
		return getProductBonus( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productBonus</code> attribute. 
	 * @param value the productBonus
	 */
	public void setProductBonus(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTBONUS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productBonus</code> attribute. 
	 * @param value the productBonus
	 */
	public void setProductBonus(final Product item, final String value)
	{
		setProductBonus( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productBonusFlag</code> attribute.
	 * @return the productBonusFlag
	 */
	public String getProductBonusFlag(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTBONUSFLAG);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productBonusFlag</code> attribute.
	 * @return the productBonusFlag
	 */
	public String getProductBonusFlag(final Product item)
	{
		return getProductBonusFlag( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productBonusFlag</code> attribute. 
	 * @param value the productBonusFlag
	 */
	public void setProductBonusFlag(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTBONUSFLAG,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productBonusFlag</code> attribute. 
	 * @param value the productBonusFlag
	 */
	public void setProductBonusFlag(final Product item, final String value)
	{
		setProductBonusFlag( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productMinimumLimit</code> attribute.
	 * @return the productMinimumLimit
	 */
	public String getProductMinimumLimit(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTMINIMUMLIMIT);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productMinimumLimit</code> attribute.
	 * @return the productMinimumLimit
	 */
	public String getProductMinimumLimit(final Product item)
	{
		return getProductMinimumLimit( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productMinimumLimit</code> attribute. 
	 * @param value the productMinimumLimit
	 */
	public void setProductMinimumLimit(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTMINIMUMLIMIT,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productMinimumLimit</code> attribute. 
	 * @param value the productMinimumLimit
	 */
	public void setProductMinimumLimit(final Product item, final String value)
	{
		setProductMinimumLimit( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productPointRatio</code> attribute.
	 * @return the productPointRatio
	 */
	public String getProductPointRatio(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTPOINTRATIO);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productPointRatio</code> attribute.
	 * @return the productPointRatio
	 */
	public String getProductPointRatio(final Product item)
	{
		return getProductPointRatio( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productPointRatio</code> attribute. 
	 * @param value the productPointRatio
	 */
	public void setProductPointRatio(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTPOINTRATIO,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productPointRatio</code> attribute. 
	 * @param value the productPointRatio
	 */
	public void setProductPointRatio(final Product item, final String value)
	{
		setProductPointRatio( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productPoints</code> attribute.
	 * @return the productPoints
	 */
	public String getProductPoints(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTPOINTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productPoints</code> attribute.
	 * @return the productPoints
	 */
	public String getProductPoints(final Product item)
	{
		return getProductPoints( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productPoints</code> attribute. 
	 * @param value the productPoints
	 */
	public void setProductPoints(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTPOINTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productPoints</code> attribute. 
	 * @param value the productPoints
	 */
	public void setProductPoints(final Product item, final String value)
	{
		setProductPoints( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productPointType</code> attribute.
	 * @return the productPointType
	 */
	public String getProductPointType(final SessionContext ctx, final Product item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTPOINTTYPE);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Product.productPointType</code> attribute.
	 * @return the productPointType
	 */
	public String getProductPointType(final Product item)
	{
		return getProductPointType( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productPointType</code> attribute. 
	 * @param value the productPointType
	 */
	public void setProductPointType(final SessionContext ctx, final Product item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Product.PRODUCTPOINTTYPE,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Product.productPointType</code> attribute. 
	 * @param value the productPointType
	 */
	public void setProductPointType(final Product item, final String value)
	{
		setProductPointType( getSession().getSessionContext(), item, value );
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.usedPoints</code> attribute.
	 * @return the usedPoints
	 */
	public String getUsedPoints(final SessionContext ctx, final Customer item)
	{
		return (String)item.getProperty( ctx, Annexcloudv3servicesConstants.Attributes.Customer.USEDPOINTS);
	}
	
	/**
	 * <i>Generated method</i> - Getter of the <code>Customer.usedPoints</code> attribute.
	 * @return the usedPoints
	 */
	public String getUsedPoints(final Customer item)
	{
		return getUsedPoints( getSession().getSessionContext(), item );
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.usedPoints</code> attribute. 
	 * @param value the usedPoints
	 */
	public void setUsedPoints(final SessionContext ctx, final Customer item, final String value)
	{
		item.setProperty(ctx, Annexcloudv3servicesConstants.Attributes.Customer.USEDPOINTS,value);
	}
	
	/**
	 * <i>Generated method</i> - Setter of the <code>Customer.usedPoints</code> attribute. 
	 * @param value the usedPoints
	 */
	public void setUsedPoints(final Customer item, final String value)
	{
		setUsedPoints( getSession().getSessionContext(), item, value );
	}
	
}
