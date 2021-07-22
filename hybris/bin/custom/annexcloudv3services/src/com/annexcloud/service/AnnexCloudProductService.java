package com.annexcloud.service;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.List;

public interface AnnexCloudProductService {
    public List<ProductModel> findProductsByCatalogVersion(final CatalogVersionModel catalogVersion);
}
