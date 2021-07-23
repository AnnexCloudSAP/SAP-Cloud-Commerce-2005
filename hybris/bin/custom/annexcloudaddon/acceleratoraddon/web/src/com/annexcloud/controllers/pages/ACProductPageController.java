/**

*********  ****     ***   ****     ***  ********* **       **
*********  *****    ***   *****    ***  *********  **     **
***   ***  *** **   ***   *** **   ***  ***         **   **
***   ***  *** **   ***   *** **   ***  *********    ** **
*********  ***  **  ***   ***  **  ***  *********     **
***   ***  ***   ** ***   ***   ** ***  ***         ** **
***   ***  ***    * ***   ***    * ***  *********  **   **
***   ***  ***     ****   ***     ****  ********* **     **


 ********   ***          *******    ***   ***   ********
*********   ***         *********   ***   ***   *********
***         ***         ***   ***   ***   ***   ***   ***
***         ***         ***   ***   ***   ***   ***   ***
***         ***         ***   ***   ***   ***   ***   ***
***         ***         ***   ***   ***   ***   ***   ***
*********   *********   *********   *********   *********
 ********   *********    *******     *******    ********


 Annex cloud Copyright (c) 2019
 All software and accompanying documents that you download from Annex Cloud
 are the copyrighted work of Annex Cloud and/or its suppliers. Your use of
 the Software is governed by the terms of the software license agreement
 applicable to the Software ("License Agreement"). You are not authorized to
 install or use any Software unless you first agree to the License Agreement
 terms. All rights, title, and interest to the Software not expressly granted
 are reserved.*/
package com.annexcloud.controllers.pages;

import static com.annexcloud.controllers.ControllerConstants.Actions.Cms.PRODUCT_CODE_PATH_VARIABLE_PATTERN;

import de.hybris.platform.acceleratorfacades.futurestock.FutureStockFacade;
import de.hybris.platform.acceleratorservices.controllers.page.PageType;
import de.hybris.platform.acceleratorservices.urlresolver.SiteBaseUrlResolutionService;
import de.hybris.platform.acceleratorstorefrontcommons.breadcrumb.impl.ProductBreadcrumbBuilder;
import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.FutureStockForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ReviewForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.ReviewValidator;
import de.hybris.platform.acceleratorstorefrontcommons.util.MetaSanitizerUtil;
import de.hybris.platform.acceleratorstorefrontcommons.util.XSSFilterUtil;
import de.hybris.platform.acceleratorstorefrontcommons.variants.VariantSortStrategy;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.site.CMSSiteModel;
import de.hybris.platform.cms2.servicelayer.services.CMSPageService;
import de.hybris.platform.cms2.servicelayer.services.CMSSiteService;
import de.hybris.platform.commercefacades.order.data.ConfigurationInfoData;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.BaseOptionData;
import de.hybris.platform.commercefacades.product.data.FutureStockData;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ImageDataType;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.product.data.ReviewData;
import de.hybris.platform.commercefacades.user.UserFacade;
import de.hybris.platform.commerceservices.url.UrlResolver;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.util.Config;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.annex.cloud.data.customer.AnnexCloudData;
import com.annex.cloud.product.data.AnnexCloudProductDetailsData;
import com.annexcloud.controllers.ControllerConstants;
import com.annexcloud.facade.AnnexCloudV3Facade;
import com.google.common.collect.Maps;


// TODO: Auto-generated Javadoc
/**
 * The Class ACProductPageController.
 */

@RequestMapping(value = "/**/p")
public class ACProductPageController extends AbstractPageController
{

	/** The Constant LOG. */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ACProductPageController.class);

	/** The Constant REVIEWS_PATH_VARIABLE_PATTERN. */
	private static final String REVIEWS_PATH_VARIABLE_PATTERN = "{numberOfReviews:.*}";

	/** The Constant FUTURE_STOCK_ENABLED. */
	private static final String FUTURE_STOCK_ENABLED = "storefront.products.futurestock.enabled";

	/** The Constant STOCK_SERVICE_UNAVAILABLE. */
	private static final String STOCK_SERVICE_UNAVAILABLE = "basket.page.viewFuture.unavailable";

	/** The Constant NOT_MULTISKU_ITEM_ERROR. */
	private static final String NOT_MULTISKU_ITEM_ERROR = "basket.page.viewFuture.not.multisku";

	/** The product data url resolver. */
	@Resource(name = "productDataUrlResolver")
	private UrlResolver<ProductData> productDataUrlResolver;

	/** The product facade. */
	@Resource(name = "productVariantFacade")
	private ProductFacade productFacade;

	/** The product service. */
	@Resource(name = "productService")
	private ProductService productService;

	/** The annex cloud loyalty facade. */
	@Resource(name = "acV3LoyaltyFacade")
	private AnnexCloudV3Facade annexCloudLoyaltyFacade;

	/** The product breadcrumb builder. */
	@Resource(name = "productBreadcrumbBuilder")
	private ProductBreadcrumbBuilder productBreadcrumbBuilder;

	/** The cms page service. */
	@Resource(name = "cmsPageService")
	private CMSPageService cmsPageService;

	/** The variant sort strategy. */
	@Resource(name = "variantSortStrategy")
	private VariantSortStrategy variantSortStrategy;

	/** The review validator. */
	@Resource(name = "reviewValidator")
	private ReviewValidator reviewValidator;

	/** The future stock facade. */
	@Resource(name = "futureStockFacade")
	private FutureStockFacade futureStockFacade;

	/** The user facade. */
	@Resource(name = "userFacade")
	private UserFacade userFacade;

	/** The cms site service. */
	@Resource(name = "cmsSiteService")
	private CMSSiteService cmsSiteService;

	/** The site base url resolution service. */
	@Resource(name = "siteBaseUrlResolutionService")
	private SiteBaseUrlResolutionService siteBaseUrlResolutionService;


	/**
	 * Product detail.
	 *
	 * @param encodedProductCode
	 *           the encoded product code
	 * @param model
	 *           the model
	 * @param request
	 *           the request
	 * @param response
	 *           the response
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 * @throws UnsupportedEncodingException
	 *            the unsupported encoding exception
	 */
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String productDetail(@PathVariable("productCode")
	final String encodedProductCode, final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException, UnsupportedEncodingException
	{
		final String productCode = decodeWithScheme(encodedProductCode, UTF_8);
		final List<ProductOption> extraOptions = Arrays.asList(ProductOption.VARIANT_MATRIX_BASE, ProductOption.VARIANT_MATRIX_URL,
				ProductOption.VARIANT_MATRIX_MEDIA);

		final ProductData productData = productFacade.getProductForCodeAndOptions(productCode, extraOptions);
		final String redirection = checkRequestUrl(request, response, productDataUrlResolver.resolve(productData));
		if (StringUtils.isNotEmpty(redirection))
		{
			return redirection;
		}

		updatePageTitle(productCode, model);

		populateProductDetailForDisplay(productCode, model, request, extraOptions);

		model.addAttribute(new ReviewForm());
		model.addAttribute("pageType", PageType.PRODUCT.name());
		model.addAttribute("futureStockEnabled", Boolean.valueOf(Config.getBoolean(FUTURE_STOCK_ENABLED, false)));
		final CMSSiteModel currentSite = cmsSiteService.getCurrentSite();
		final String mediaUrlForSite = siteBaseUrlResolutionService.getMediaUrlForSite(currentSite, true, "");
		model.addAttribute("productMediaUrl", mediaUrlForSite + productData.getImages().stream().findAny().get().getUrl());
		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(productData.getKeywords());
		final String metaDescription = MetaSanitizerUtil.sanitizeDescription(productData.getDescription());
		setUpMetaData(model, metaKeywords, metaDescription);
		return getViewForPage(model);
	}

	/**
	 * Product order form.
	 *
	 * @param encodedProductCode
	 *           the encoded product code
	 * @param model
	 *           the model
	 * @param request
	 *           the request
	 * @param response
	 *           the response
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/orderForm", method = RequestMethod.GET)
	public String productOrderForm(@PathVariable("productCode")
	final String encodedProductCode, final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException
	{
		final String productCode = decodeWithScheme(encodedProductCode, UTF_8);
		final List<ProductOption> extraOptions = Arrays.asList(ProductOption.VARIANT_MATRIX_BASE,
				ProductOption.VARIANT_MATRIX_PRICE, ProductOption.VARIANT_MATRIX_MEDIA, ProductOption.VARIANT_MATRIX_STOCK,
				ProductOption.URL);

		final ProductData productData = productFacade.getProductForCodeAndOptions(productCode, extraOptions);
		updatePageTitle(productCode, model);

		populateProductDetailForDisplay(productCode, model, request, extraOptions);

		if (!model.containsAttribute(WebConstants.MULTI_DIMENSIONAL_PRODUCT))
		{
			return REDIRECT_PREFIX + productDataUrlResolver.resolve(productData);
		}

		return ControllerConstants.Views.Pages.Product.OrderForm;
	}

	/**
	 * Show zoom images.
	 *
	 * @param encodedProductCode
	 *           the encoded product code
	 * @param galleryPosition
	 *           the gallery position
	 * @param model
	 *           the model
	 * @return the string
	 */
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/zoomImages", method = RequestMethod.GET)
	public String showZoomImages(@PathVariable("productCode")
	final String encodedProductCode, @RequestParam(value = "galleryPosition", required = false)
	final String galleryPosition, final Model model)
	{
		final String productCode = decodeWithScheme(encodedProductCode, UTF_8);
		final ProductData productData = productFacade.getProductForCodeAndOptions(productCode,
				Collections.singleton(ProductOption.GALLERY));
		final List<Map<String, ImageData>> images = getGalleryImages(productData);
		populateProductData(productData, model);
		if (galleryPosition != null)
		{
			try
			{
				model.addAttribute("zoomImageUrl", images.get(Integer.parseInt(galleryPosition)).get("zoom").getUrl());
			}
			catch (final IndexOutOfBoundsException | NumberFormatException ex)
			{
				if (LOG.isDebugEnabled())
				{
					LOG.debug(ex);
				}
				model.addAttribute("zoomImageUrl", "");
			}
		}
		return ControllerConstants.Views.Fragments.Product.ZoomImagesPopup;
	}

	/**
	 * Show quick view.
	 *
	 * @param encodedProductCode
	 *           the encoded product code
	 * @param model
	 *           the model
	 * @param request
	 *           the request
	 * @return the string
	 */
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/quickView", method = RequestMethod.GET)
	public String showQuickView(@PathVariable("productCode")
	final String encodedProductCode, final Model model, final HttpServletRequest request)
	{
		final String productCode = decodeWithScheme(encodedProductCode, UTF_8);
		final ProductModel productModel = productService.getProductForCode(productCode);
		final ProductData productData = productFacade.getProductForCodeAndOptions(productCode,
				Arrays.asList(ProductOption.BASIC, ProductOption.PRICE, ProductOption.SUMMARY, ProductOption.DESCRIPTION,
						ProductOption.CATEGORIES, ProductOption.PROMOTIONS, ProductOption.STOCK, ProductOption.REVIEW,
						ProductOption.VARIANT_FULL, ProductOption.DELIVERY_MODE_AVAILABILITY));

		sortVariantOptionData(productData);
		populateProductData(productData, model);
		getRequestContextData(request).setProduct(productModel);

		return ControllerConstants.Views.Fragments.Product.QuickViewPopup;
	}

	/**
	 * Post review.
	 *
	 * @param encodedProductCode
	 *           the encoded product code
	 * @param form
	 *           the form
	 * @param result
	 *           the result
	 * @param model
	 *           the model
	 * @param request
	 *           the request
	 * @param redirectAttrs
	 *           the redirect attrs
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/review", method =
	{ RequestMethod.GET, RequestMethod.POST })
	public String postReview(@PathVariable("productCode")
	final String encodedProductCode, final ReviewForm form, final BindingResult result, final Model model,
			final HttpServletRequest request, final RedirectAttributes redirectAttrs) throws CMSItemNotFoundException
	{
		final String productCode = decodeWithScheme(encodedProductCode, UTF_8);
		getReviewValidator().validate(form, result);

		final ProductData productData = productFacade.getProductForCodeAndOptions(productCode, null);
		if (result.hasErrors())
		{
			updatePageTitle(productCode, model);
			GlobalMessages.addErrorMessage(model, "review.general.error");
			model.addAttribute("showReviewForm", Boolean.TRUE);
			populateProductDetailForDisplay(productCode, model, request, Collections.emptyList());
			storeCmsPageInModel(model, getPageForProduct(productCode));
			return getViewForPage(model);
		}

		final ReviewData review = new ReviewData();
		review.setHeadline(XSSFilterUtil.filter(form.getHeadline()));
		review.setComment(XSSFilterUtil.filter(form.getComment()));
		review.setRating(form.getRating());
		review.setAlias(XSSFilterUtil.filter(form.getAlias()));
		productFacade.postReview(productCode, review);
		GlobalMessages.addFlashMessage(redirectAttrs, GlobalMessages.CONF_MESSAGES_HOLDER, "review.confirmation.thank.you.title");

		return REDIRECT_PREFIX + productDataUrlResolver.resolve(productData);
	}

	/**
	 * Review html.
	 *
	 * @param encodedProductCode
	 *           the encoded product code
	 * @param numberOfReviews
	 *           the number of reviews
	 * @param model
	 *           the model
	 * @param request
	 *           the request
	 * @return the string
	 */
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/reviewhtml/"
			+ REVIEWS_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String reviewHtml(@PathVariable("productCode")
	final String encodedProductCode, @PathVariable("numberOfReviews")
	final String numberOfReviews, final Model model, final HttpServletRequest request)
	{
		final String productCode = decodeWithScheme(encodedProductCode, UTF_8);
		final ProductModel productModel = productService.getProductForCode(productCode);
		final List<ReviewData> reviews;
		final ProductData productData = productFacade.getProductForCodeAndOptions(productCode,
				Arrays.asList(ProductOption.BASIC, ProductOption.REVIEW));

		if ("all".equals(numberOfReviews))
		{
			reviews = productFacade.getReviews(productCode);
		}
		else
		{
			final int reviewCount = Math.min(Integer.parseInt(numberOfReviews),
					productData.getNumberOfReviews() == null ? 0 : productData.getNumberOfReviews().intValue());
			reviews = productFacade.getReviews(productCode, Integer.valueOf(reviewCount));
		}

		getRequestContextData(request).setProduct(productModel);
		model.addAttribute("reviews", reviews);
		model.addAttribute("reviewsTotal", productData.getNumberOfReviews());
		model.addAttribute(new ReviewForm());

		return ControllerConstants.Views.Fragments.Product.ReviewsTab;
	}

	/**
	 * Write review.
	 *
	 * @param encodedProductCode
	 *           the encoded product code
	 * @param model
	 *           the model
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/writeReview", method = RequestMethod.GET)
	public String writeReview(@PathVariable("productCode")
	final String encodedProductCode, final Model model) throws CMSItemNotFoundException
	{
		final String productCode = decodeWithScheme(encodedProductCode, UTF_8);
		model.addAttribute(new ReviewForm());
		setUpReviewPage(model, productCode);
		return ControllerConstants.Views.Pages.Product.WriteReview;
	}

	/**
	 * Sets the up review page.
	 *
	 * @param model
	 *           the model
	 * @param productCode
	 *           the product code
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	protected void setUpReviewPage(final Model model, final String productCode) throws CMSItemNotFoundException
	{
		final ProductData productData = productFacade.getProductForCodeAndOptions(productCode, null);
		final String metaKeywords = MetaSanitizerUtil.sanitizeKeywords(productData.getKeywords());
		final String metaDescription = MetaSanitizerUtil.sanitizeDescription(productData.getDescription());
		setUpMetaData(model, metaKeywords, metaDescription);
		storeCmsPageInModel(model, getPageForProduct(productCode));
		model.addAttribute("product", productFacade.getProductForCodeAndOptions(productCode, Arrays.asList(ProductOption.BASIC)));
		updatePageTitle(productCode, model);
	}

	/**
	 * Write review.
	 *
	 * @param encodedProductCode
	 *           the encoded product code
	 * @param form
	 *           the form
	 * @param result
	 *           the result
	 * @param model
	 *           the model
	 * @param request
	 *           the request
	 * @param redirectAttrs
	 *           the redirect attrs
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/writeReview", method = RequestMethod.POST)
	public String writeReview(@PathVariable("productCode")
	final String encodedProductCode, final ReviewForm form, final BindingResult result, final Model model,
			final HttpServletRequest request, final RedirectAttributes redirectAttrs) throws CMSItemNotFoundException
	{
		final String productCode = decodeWithScheme(encodedProductCode, UTF_8);
		getReviewValidator().validate(form, result);

		final ProductData productData = productFacade.getProductForCodeAndOptions(productCode, null);

		if (result.hasErrors())
		{
			GlobalMessages.addErrorMessage(model, "review.general.error");
			populateProductDetailForDisplay(productCode, model, request, Collections.emptyList());
			setUpReviewPage(model, productCode);
			return ControllerConstants.Views.Pages.Product.WriteReview;
		}

		final ReviewData review = new ReviewData();
		review.setHeadline(XSSFilterUtil.filter(form.getHeadline()));
		review.setComment(XSSFilterUtil.filter(form.getComment()));
		review.setRating(form.getRating());
		review.setAlias(XSSFilterUtil.filter(form.getAlias()));
		productFacade.postReview(productCode, review);
		GlobalMessages.addFlashMessage(redirectAttrs, GlobalMessages.CONF_MESSAGES_HOLDER, "review.confirmation.thank.you.title");

		return REDIRECT_PREFIX + productDataUrlResolver.resolve(productData);
	}

	/**
	 * Product future stock.
	 *
	 * @param encodedProductCode
	 *           the encoded product code
	 * @param model
	 *           the model
	 * @param request
	 *           the request
	 * @param response
	 *           the response
	 * @return the string
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/futureStock", method = RequestMethod.GET)
	public String productFutureStock(@PathVariable("productCode")
	final String encodedProductCode, final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws CMSItemNotFoundException
	{
		final String productCode = decodeWithScheme(encodedProductCode, UTF_8);
		final boolean futureStockEnabled = Config.getBoolean(FUTURE_STOCK_ENABLED, false);
		if (futureStockEnabled)
		{
			final List<FutureStockData> futureStockList = futureStockFacade.getFutureAvailability(productCode);
			if (futureStockList == null)
			{
				GlobalMessages.addErrorMessage(model, STOCK_SERVICE_UNAVAILABLE);
			}
			else if (futureStockList.isEmpty())
			{
				GlobalMessages.addInfoMessage(model, "product.product.details.future.nostock");
			}

			populateProductDetailForDisplay(productCode, model, request, Collections.emptyList());
			model.addAttribute("futureStocks", futureStockList);

			return ControllerConstants.Views.Fragments.Product.FutureStockPopup;
		}
		else
		{
			return ControllerConstants.Views.Pages.Error.ErrorNotFoundPage;
		}
	}

	/**
	 * Product skus future stock.
	 *
	 * @param form
	 *           the form
	 * @param model
	 *           the model
	 * @return the map
	 */
	@ResponseBody
	@RequestMapping(value = PRODUCT_CODE_PATH_VARIABLE_PATTERN + "/grid/skusFutureStock", method =
	{ RequestMethod.POST }, produces = MediaType.APPLICATION_JSON_VALUE)
	public final Map<String, Object> productSkusFutureStock(final FutureStockForm form, final Model model)
	{
		final String productCode = form.getProductCode();
		final List<String> skus = form.getSkus();
		final boolean futureStockEnabled = Config.getBoolean(FUTURE_STOCK_ENABLED, false);

		Map<String, Object> result = new HashMap<>();
		if (futureStockEnabled && CollectionUtils.isNotEmpty(skus) && StringUtils.isNotBlank(productCode))
		{
			final Map<String, List<FutureStockData>> futureStockData = futureStockFacade
					.getFutureAvailabilityForSelectedVariants(productCode, skus);

			if (futureStockData == null)
			{
				// future availability service is down, we show this to the user
				result = Maps.newHashMap();
				final String errorMessage = getMessageSource().getMessage(NOT_MULTISKU_ITEM_ERROR, null,
						getI18nService().getCurrentLocale());
				result.put(NOT_MULTISKU_ITEM_ERROR, errorMessage);
			}
			else
			{
				for (final Map.Entry<String, List<FutureStockData>> entry : futureStockData.entrySet())
				{
					result.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return result;
	}

	/**
	 * Handle unknown identifier exception.
	 *
	 * @param exception
	 *           the exception
	 * @param request
	 *           the request
	 * @return the string
	 */
	@ExceptionHandler(UnknownIdentifierException.class)
	public String handleUnknownIdentifierException(final UnknownIdentifierException exception, final HttpServletRequest request)
	{
		request.setAttribute("message", exception.getMessage());
		return FORWARD_PREFIX + "/404";
	}

	/**
	 * Update page title.
	 *
	 * @param productCode
	 *           the product code
	 * @param model
	 *           the model
	 */
	protected void updatePageTitle(final String productCode, final Model model)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveProductPageTitle(productCode));
	}

	/**
	 * Populate product detail for display.
	 *
	 * @param productCode
	 *           the product code
	 * @param model
	 *           the model
	 * @param request
	 *           the request
	 * @param extraOptions
	 *           the extra options
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	protected void populateProductDetailForDisplay(final String productCode, final Model model, final HttpServletRequest request,
			final List<ProductOption> extraOptions) throws CMSItemNotFoundException
	{
		final ProductModel productModel = productService.getProductForCode(productCode);

		getRequestContextData(request).setProduct(productModel);

		final List<ProductOption> options = new ArrayList<>(Arrays.asList(ProductOption.VARIANT_FIRST_VARIANT, ProductOption.BASIC,
				ProductOption.URL, ProductOption.PRICE, ProductOption.SUMMARY, ProductOption.DESCRIPTION, ProductOption.GALLERY,
				ProductOption.CATEGORIES, ProductOption.REVIEW, ProductOption.PROMOTIONS, ProductOption.CLASSIFICATION,
				ProductOption.VARIANT_FULL, ProductOption.STOCK, ProductOption.VOLUME_PRICES, ProductOption.PRICE_RANGE,
				ProductOption.DELIVERY_MODE_AVAILABILITY));

		options.addAll(extraOptions);

		final ProductData productData = productFacade.getProductForCodeAndOptions(productCode, options);

		sortVariantOptionData(productData);
		storeCmsPageInModel(model, getPageForProduct(productCode));
		populateProductData(productData, model);
		model.addAttribute(WebConstants.BREADCRUMBS_KEY, productBreadcrumbBuilder.getBreadcrumbs(productCode));

		if (!userFacade.isAnonymousUser())
		{
			if (annexCloudLoyaltyFacade.getAnnexCloudUserActiveStatus())
			{
				final AnnexCloudProductDetailsData productsDetails = annexCloudLoyaltyFacade.getProductPurchasePoints(productData);
				model.addAttribute("productPoints", productsDetails.getEarnPoints());
				model.addAttribute("productCosts", productsDetails.getCost());
				model.addAttribute("tierMultiplier", productsDetails.getTierMultiplier());

			}
		}


		if (CollectionUtils.isNotEmpty(productData.getVariantMatrix()))
		{
			model.addAttribute(WebConstants.MULTI_DIMENSIONAL_PRODUCT,
					Boolean.valueOf(CollectionUtils.isNotEmpty(productData.getVariantMatrix())));
		}
	}

	/**
	 * Populate product data.
	 *
	 * @param productData
	 *           the product data
	 * @param model
	 *           the model
	 */
	protected void populateProductData(final ProductData productData, final Model model)
	{
		final AnnexCloudData annexCloudData = annexCloudLoyaltyFacade.getAnnexCloudCredentials();
		model.addAttribute("siteId", annexCloudData.getSite());
		model.addAttribute("templateId", annexCloudData.getTemplateId());
		model.addAttribute("galleryImages", getGalleryImages(productData));
		model.addAttribute("product", productData);
		if (productData.getConfigurable())
		{
			final List<ConfigurationInfoData> configurations = productFacade.getConfiguratorSettingsForCode(productData.getCode());
			if (CollectionUtils.isNotEmpty(configurations))
			{
				model.addAttribute("configuratorType", configurations.get(0).getConfiguratorType());
			}
		}
	}

	/**
	 * Sort variant option data.
	 *
	 * @param productData
	 *           the product data
	 */
	protected void sortVariantOptionData(final ProductData productData)
	{
		if (CollectionUtils.isNotEmpty(productData.getBaseOptions()))
		{
			for (final BaseOptionData baseOptionData : productData.getBaseOptions())
			{
				if (CollectionUtils.isNotEmpty(baseOptionData.getOptions()))
				{
					Collections.sort(baseOptionData.getOptions(), variantSortStrategy);
				}
			}
		}

		if (CollectionUtils.isNotEmpty(productData.getVariantOptions()))
		{
			Collections.sort(productData.getVariantOptions(), variantSortStrategy);
		}
	}

	/**
	 * Gets the gallery images.
	 *
	 * @param productData
	 *           the product data
	 * @return the gallery images
	 */
	protected List<Map<String, ImageData>> getGalleryImages(final ProductData productData)
	{
		final List<Map<String, ImageData>> galleryImages = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(productData.getImages()))
		{
			final List<ImageData> images = new ArrayList<>();
			for (final ImageData image : productData.getImages())
			{
				if (ImageDataType.GALLERY.equals(image.getImageType()))
				{
					images.add(image);
				}
			}
			Collections.sort(images, new Comparator<ImageData>()
			{
				@Override
				public int compare(final ImageData image1, final ImageData image2)
				{
					return image1.getGalleryIndex().compareTo(image2.getGalleryIndex());
				}
			});

			if (CollectionUtils.isNotEmpty(images))
			{
				addFormatsToGalleryImages(galleryImages, images);
			}
		}
		return galleryImages;
	}

	/**
	 * Adds the formats to gallery images.
	 *
	 * @param galleryImages
	 *           the gallery images
	 * @param images
	 *           the images
	 */
	protected void addFormatsToGalleryImages(final List<Map<String, ImageData>> galleryImages, final List<ImageData> images)
	{
		int currentIndex = images.get(0).getGalleryIndex().intValue();
		Map<String, ImageData> formats = new HashMap<>();
		for (final ImageData image : images)
		{
			if (currentIndex != image.getGalleryIndex().intValue())
			{
				galleryImages.add(formats);
				formats = new HashMap<>();
				currentIndex = image.getGalleryIndex().intValue();
			}
			formats.put(image.getFormat(), image);
		}
		if (!formats.isEmpty())
		{
			galleryImages.add(formats);
		}
	}

	/**
	 * Gets the review validator.
	 *
	 * @return the review validator
	 */
	protected ReviewValidator getReviewValidator()
	{
		return reviewValidator;
	}

	/**
	 * Gets the page for product.
	 *
	 * @param productCode
	 *           the product code
	 * @return the page for product
	 * @throws CMSItemNotFoundException
	 *            the CMS item not found exception
	 */
	protected AbstractPageModel getPageForProduct(final String productCode) throws CMSItemNotFoundException
	{
		final ProductModel productModel = productService.getProductForCode(productCode);
		return cmsPageService.getPageForProduct(productModel, getCmsPreviewService().getPagePreviewCriteria());
	}

}
