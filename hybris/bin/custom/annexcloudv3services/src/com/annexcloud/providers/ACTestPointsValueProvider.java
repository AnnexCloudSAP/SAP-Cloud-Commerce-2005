package com.annexcloud.providers;

import com.annex.cloud.loyalty.jwt.ACProductJwtRequest;
import com.annex.cloud.loyalty.jwt.ACProductWrapperJwtRequest;
import com.annex.cloud.v3.api.response.user.ACProductPointWrapperResponse;
import com.annexcloud.service.AnnexCloudLoyaltyService;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;
import de.hybris.platform.store.BaseStoreModel;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class ACTestPointsValueProvider extends AbstractPropertyFieldValueProvider implements FieldValueProvider {

    private static final Logger LOG = Logger.getLogger(String.valueOf(AnnexCloudProductRatingValueProvider.class));

    private FieldNameProvider fieldNameProvider;

    private AnnexCloudLoyaltyService annexCloudLoyaltyService;


    @Override
    public Collection<FieldValue> getFieldValues(IndexConfig indexConfig, IndexedProperty indexedProperty, final Object obj) throws FieldValueProviderException {
            if (obj instanceof ProductModel)
            {
                final ProductModel product = (ProductModel) obj;
                final List<FieldValue> fieldValues = createFieldValue(product, indexConfig, indexedProperty);
                return fieldValues;
            }
            else
            {
                throw new FieldValueProviderException("Cannot get online days for a product model");
            }
        }

    private List<FieldValue> createFieldValue(ProductModel product, IndexConfig indexConfig, IndexedProperty indexedProperty) {
        final List<FieldValue> fieldValues = new ArrayList<>();
        final BaseStoreModel store = indexConfig.getBaseSite().getStores().get(0);
        final ACProductJwtRequest productJwtRequest = new ACProductJwtRequest();
        productJwtRequest.setId(product.getCode());
        productJwtRequest.setQuantity("1");
        productJwtRequest.setUnitPrice(product.getEurope1Prices().stream()
                .filter(p -> p.getCurrency().getIsocode().equalsIgnoreCase(store.getDefaultCurrency().getIsocode())).findFirst().get()
                .getPrice().toString());
        productJwtRequest.setAutoDelivery("NO");

        //add productInfo model to list
        final List<ACProductJwtRequest> productMap = new ArrayList();
        productMap.add(productJwtRequest);

        //set list to wrapper class with user
        final ACProductWrapperJwtRequest productWrapper = new ACProductWrapperJwtRequest();
        productWrapper.setProductDetail(productMap);

        final ACProductPointWrapperResponse response = annexCloudLoyaltyService.productInformationAPI(productWrapper,
                store.getAnnexCloud());

        if (response.getRequestHasSucceeded())
        {
            addFieldValues(fieldValues, indexedProperty, null, response.getData().get(0).getPoints());
        }
        return fieldValues;
    }

    protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
                                  final LanguageModel language, final Object value)
    {
        List<String> rangeName = null;
        try
        {
            rangeName = this.getRangeNameList(indexedProperty, value);
        }
        catch (final FieldValueProviderException e)
        {
            LOG.info("Could not get Range value");
        }
        final Collection<String> fieldNames = getFieldNameProvider().getFieldNames(indexedProperty,
                language == null ? null : language.getIsocode());
        for (final String fieldName : fieldNames)
        {
            fieldValues.add(new FieldValue(fieldName, rangeName.isEmpty() ? value : rangeName));
        }
    }

    public FieldNameProvider getFieldNameProvider()
    {
        return fieldNameProvider;
    }

    @Required
    public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
    {
        this.fieldNameProvider = fieldNameProvider;
    }

    /**
     * @return the annexCloudLoyaltyService
     */
    public AnnexCloudLoyaltyService getAnnexCloudLoyaltyService()
    {
        return annexCloudLoyaltyService;
    }

    /**
     * @param annexCloudLoyaltyService
     *           the annexCloudLoyaltyService to set
     */
    @Required
    public void setAnnexCloudLoyaltyService(final AnnexCloudLoyaltyService annexCloudLoyaltyService)
    {
        this.annexCloudLoyaltyService = annexCloudLoyaltyService;
    }


}