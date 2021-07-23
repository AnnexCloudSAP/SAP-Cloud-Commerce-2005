package com.annexcloud.service.impl;

import com.annexcloud.service.AnnexCloudProductService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNull;

public class DefaultAnnexCloudProductService extends DefaultGenericDao<ProductModel> implements AnnexCloudProductService {
    public DefaultAnnexCloudProductService(String typecode) {
        super(typecode);
    }

    public List<ProductModel> findProductsByCatalogVersion(final CatalogVersionModel catalogVersion)
    {
        validateParameterNotNull(catalogVersion, "CatalogVersion must not be null!");
        final StringBuilder query = new StringBuilder();
        query.append("SELECT {" + ProductModel.PK + "}  FROM {" + ProductModel._TYPECODE+"}");
        query.append(" WHERE {"+ProductModel.ACSYNCED+"}=?acSynced OR "+"{"+ProductModel.ACSYNCED+"} is NULL ");
        query.append(" AND   {" + ProductModel.CATALOGVERSION + "}=?catalogVersion");
        final Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(ProductModel.ACSYNCED, Boolean.FALSE.toString());
        parameters.put(ProductModel.CATALOGVERSION, catalogVersion);

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query.toString());
        searchQuery.addQueryParameters(parameters);
        searchQuery.setResultClassList(Collections.singletonList(ProductModel.class));
        final SearchResult searchResult = getFlexibleSearchService().search(searchQuery);
        return searchResult.getResult();
    }
}
