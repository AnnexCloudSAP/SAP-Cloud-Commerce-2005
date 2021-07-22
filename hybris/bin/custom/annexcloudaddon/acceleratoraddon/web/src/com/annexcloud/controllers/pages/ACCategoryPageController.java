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

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractCategoryPageController;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commercefacades.search.data.SearchStateData;
import de.hybris.platform.commerceservices.search.facetdata.FacetRefinement;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.annex.cloud.data.customer.AnnexCloudData;
import com.annexcloud.facade.AnnexCloudV3Facade;


// TODO: Auto-generated Javadoc
/**
 * The Class ACCategoryPageController.
 */
@RequestMapping(value = "/**/c")
public class ACCategoryPageController extends AbstractCategoryPageController
{

	/** The annex cloud loyalty facade. */
	@Resource(name = "acV3LoyaltyFacade")
	private AnnexCloudV3Facade annexCloudLoyaltyFacade;

	/**
	 * Category.
	 *
	 * @param categoryCode the category code
	 * @param searchQuery the search query
	 * @param page the page
	 * @param showMode the show mode
	 * @param sortCode the sort code
	 * @param model the model
	 * @param request the request
	 * @param response the response
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@RequestMapping(value = CATEGORY_CODE_PATH_VARIABLE_PATTERN, method = RequestMethod.GET)
	public String category(@PathVariable("categoryCode")
	final String categoryCode, // NOSONAR
			@RequestParam(value = "q", required = false)
			final String searchQuery, @RequestParam(value = "page", defaultValue = "0")
			final int page, @RequestParam(value = "show", defaultValue = "Page")
			final ShowMode showMode, @RequestParam(value = "sort", required = false)
			final String sortCode, final Model model, final HttpServletRequest request, final HttpServletResponse response)
			throws UnsupportedEncodingException
	{
		model.addAttribute("categoryCode", categoryCode);
		final AnnexCloudData annexCloudData = annexCloudLoyaltyFacade.getAnnexCloudCredentials();
		if (annexCloudData != null)
		{
			model.addAttribute("siteId", annexCloudData.getSite());
		}

		return performSearchAndGetResultsPage(categoryCode, searchQuery, page, showMode, sortCode, model, request, response);
	}

	/**
	 * Gets the facets.
	 *
	 * @param categoryCode the category code
	 * @param searchQuery the search query
	 * @param page the page
	 * @param showMode the show mode
	 * @param sortCode the sort code
	 * @return the facets
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@ResponseBody
	@RequestMapping(value = CATEGORY_CODE_PATH_VARIABLE_PATTERN + "/facets", method = RequestMethod.GET)
	public FacetRefinement<SearchStateData> getFacets(@PathVariable("categoryCode")
	final String categoryCode, @RequestParam(value = "q", required = false)
	final String searchQuery, @RequestParam(value = "page", defaultValue = "0")
	final int page, @RequestParam(value = "show", defaultValue = "Page")
	final ShowMode showMode, @RequestParam(value = "sort", required = false)
	final String sortCode) throws UnsupportedEncodingException
	{
		return performSearchAndGetFacets(categoryCode, searchQuery, page, showMode, sortCode);
	}

	/**
	 * Gets the results.
	 *
	 * @param categoryCode the category code
	 * @param searchQuery the search query
	 * @param page the page
	 * @param showMode the show mode
	 * @param sortCode the sort code
	 * @return the results
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@ResponseBody
	@RequestMapping(value = CATEGORY_CODE_PATH_VARIABLE_PATTERN + "/results", method = RequestMethod.GET)
	public SearchResultsData<ProductData> getResults(@PathVariable("categoryCode")
	final String categoryCode, @RequestParam(value = "q", required = false)
	final String searchQuery, @RequestParam(value = "page", defaultValue = "0")
	final int page, @RequestParam(value = "show", defaultValue = "Page")
	final ShowMode showMode, @RequestParam(value = "sort", required = false)
	final String sortCode) throws UnsupportedEncodingException
	{
		return performSearchAndGetResultsData(categoryCode, searchQuery, page, showMode, sortCode);
	}
}
