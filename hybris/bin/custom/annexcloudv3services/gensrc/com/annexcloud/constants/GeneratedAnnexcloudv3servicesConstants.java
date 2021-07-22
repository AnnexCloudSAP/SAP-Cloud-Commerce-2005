/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN! ---
 * --- Generated at 22-Jul-2021, 2:28:03 PM                     ---
 * ----------------------------------------------------------------
 */
package com.annexcloud.constants;

/**
 * @deprecated since ages - use constants in Model classes instead
 */
@Deprecated(since = "ages", forRemoval = false)
@SuppressWarnings({"unused","cast"})
public class GeneratedAnnexcloudv3servicesConstants
{
	public static final String EXTENSIONNAME = "annexcloudv3services";
	public static class TC
	{
		public static final String ACPKTYPE = "AcPkType".intern();
		public static final String ANNEXCLOUD = "AnnexCloud".intern();
		public static final String ANNEXCLOUDUSERCREDITPOINT = "AnnexCloudUserCreditPoint".intern();
		public static final String CREDITDEBITTYPE = "CreditDebitType".intern();
		public static final String LOYALTYENROLLMENTTYPE = "LoyaltyEnrollmentType".intern();
		public static final String LOYALTYPROGRAM = "LoyaltyProgram".intern();
		public static final String POINTREDEMPTIONTYPE = "PointRedemptionType".intern();
	}
	public static class Attributes
	{
		public static class AbstractOrder
		{
			public static final String ANNEXCLOUDUSERCREDITPOINT = "annexCloudUserCreditPoint".intern();
			public static final String APPLIEDANNEXCLOUDCOUPONCODE = "appliedAnnexCloudCouponCode".intern();
		}
		public static class BaseStore
		{
			public static final String ANNEXCLOUD = "annexCloud".intern();
		}
		public static class Category
		{
			public static final String CATEGORYBONUSFLAG = "categoryBonusFlag".intern();
			public static final String CATEGORYPOINTRATIO = "categoryPointRatio".intern();
			public static final String CATEGORYPOINTTYPE = "categoryPointType".intern();
		}
		public static class Customer
		{
			public static final String ACID = "acId".intern();
			public static final String AVAILABLEPOINTS = "availablePoints".intern();
			public static final String CURRENTTIER = "currentTier".intern();
			public static final String LIFETIMEPOINTS = "lifetimePoints".intern();
			public static final String NEXTTIER = "nextTier".intern();
			public static final String OPTINDATE = "optInDate".intern();
			public static final String OPTINSTATUS = "optInStatus".intern();
			public static final String POINTSTOEXPIRE = "pointsToExpire".intern();
			public static final String POINTSTOEXPIREDATE = "pointsToExpireDate".intern();
			public static final String POINTSTONEXTTIER = "pointsToNextTier".intern();
			public static final String USEDPOINTS = "usedPoints".intern();
		}
		public static class CxSegment
		{
			public static final String ACSEGMENTID = "acSegmentId".intern();
		}
		public static class Product
		{
			public static final String ACSYNCED = "acSynced".intern();
			public static final String AVERAGERATINGS = "averageRatings".intern();
			public static final String POINTAWARDTYPE = "pointAwardType".intern();
			public static final String PRODUCTBONUS = "productBonus".intern();
			public static final String PRODUCTBONUSFLAG = "productBonusFlag".intern();
			public static final String PRODUCTMINIMUMLIMIT = "productMinimumLimit".intern();
			public static final String PRODUCTPOINTRATIO = "productPointRatio".intern();
			public static final String PRODUCTPOINTS = "productPoints".intern();
			public static final String PRODUCTPOINTTYPE = "productPointType".intern();
		}
	}
	public static class Enumerations
	{
		public static class AcPkType
		{
			public static final String UID = "UID".intern();
			public static final String GYUID = "GYUID".intern();
		}
		public static class CreditDebitType
		{
			public static final String CREDIT = "CREDIT".intern();
			public static final String DEBIT = "DEBIT".intern();
			public static final String NONE = "NONE".intern();
		}
		public static class LoyaltyEnrollmentType
		{
			public static final String OPTIN = "OPTIN".intern();
			public static final String OPTOUT = "OPTOUT".intern();
			public static final String NONE = "NONE".intern();
		}
		public static class LoyaltyProgram
		{
			public static final String IMPLICIT = "IMPLICIT".intern();
			public static final String EXPLICIT = "EXPLICIT".intern();
		}
		public static class PaymentTransactionType
		{
			public static final String LOYALTY_POINT_CREDIT = "LOYALTY_POINT_CREDIT".intern();
		}
		public static class PointRedemptionType
		{
			public static final String PRICE = "PRICE".intern();
			public static final String COUPON = "COUPON".intern();
		}
	}
	
	protected GeneratedAnnexcloudv3servicesConstants()
	{
		// private constructor
	}
	
	
}
