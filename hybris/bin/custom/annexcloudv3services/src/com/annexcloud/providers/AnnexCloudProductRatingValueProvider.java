package com.annexcloud.providers;

import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Required;

public class AnnexCloudProductRatingValueProvider extends AbstractPropertyFieldValueProvider
        implements FieldValueProvider {

  private static final Logger LOG = Logger.getLogger(String.valueOf(AnnexCloudProductRatingValueProvider.class));

  private FieldNameProvider fieldNameProvider;

  @SuppressWarnings("deprecation")
  @Override
  public Collection<FieldValue> getFieldValues(final IndexConfig indexConfig, final IndexedProperty indexedProperty,
          final Object obj) throws FieldValueProviderException {
    if (obj instanceof ProductModel) {
      final ProductModel product = (ProductModel) obj;
      return createFieldValue(product, indexConfig, indexedProperty);

    } else {
      throw new FieldValueProviderException("Cannot get value for annexreview for product model");
    }
  }

  @SuppressWarnings("boxing")
  protected List<FieldValue> createFieldValue(final ProductModel product, final IndexConfig indexConfig,
          final IndexedProperty indexedProperty) {
    final List<FieldValue> fieldValues = new ArrayList<>();
    final Double annexCloudRating = product.getAverageRatings();
    if (annexCloudRating != null) {
      addFieldValues(fieldValues, indexedProperty, null, annexCloudRating);
    }
    return fieldValues;
  }

  protected void addFieldValues(final List<FieldValue> fieldValues, final IndexedProperty indexedProperty,
          final LanguageModel language, final Object value) {
    List<String> rangeName = null;
    try {
      rangeName = this.getRangeNameList(indexedProperty, value);
    } catch (final FieldValueProviderException e) {
      LOG.info("Could not get Range value");
    }
    final Collection<String> fieldNames =
            getFieldNameProvider().getFieldNames(indexedProperty, language == null ? null : language.getIsocode());
    for (final String fieldName : fieldNames) {
      fieldValues.add(new FieldValue(fieldName, rangeName == null ? value : rangeName));
    }
  }

  public FieldNameProvider getFieldNameProvider() {
    return fieldNameProvider;
  }

  @Required
  public void setFieldNameProvider(final FieldNameProvider fieldNameProvider) {
    this.fieldNameProvider = fieldNameProvider;
  }
}
