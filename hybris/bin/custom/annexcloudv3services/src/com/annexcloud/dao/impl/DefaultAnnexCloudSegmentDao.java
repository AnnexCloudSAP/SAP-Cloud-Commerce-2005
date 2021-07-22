package com.annexcloud.dao.impl;

import com.annexcloud.dao.AnnexCloudSegmentDao;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.personalizationservices.model.CxSegmentModel;
import de.hybris.platform.personalizationservices.model.CxUserToSegmentModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.search.SearchResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultAnnexCloudSegmentDao extends DefaultGenericDao<CxUserToSegmentModel> implements AnnexCloudSegmentDao {


    private static final Logger LOG = Logger.getLogger(DefaultAnnexCloudSegmentDao.class);


    public DefaultAnnexCloudSegmentDao()
    {
        super(CxUserToSegmentModel._TYPECODE);
    }
/*
    final StringBuilder queryWithSegment = new StringBuilder("SELECT {").append(CxUserToSegmentModel.PK).append("} FROM  {")
            .append(CxUserToSegmentModel._TYPECODE).append("} WHERE {").append(CxUserToSegmentModel.BASESITE)
            .append("} = ?").append(CxUserToSegmentModel.BASESITE);*/
    @Override
    public List<CxSegmentModel> getUserToSegment(BaseSiteModel site) {
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put(CxUserToSegmentModel.BASESITE,site);
        final StringBuilder query = new StringBuilder("SELECT {").append(CxUserToSegmentModel.SEGMENT).append("} FROM  {")
                .append(CxUserToSegmentModel._TYPECODE).append("} WHERE {").append(CxUserToSegmentModel.BASESITE)
                .append("} = ?").append(CxUserToSegmentModel.BASESITE).append(" group by {").append(CxUserToSegmentModel.SEGMENT)
                .append("}");

        final SearchResult<CxSegmentModel> results = getFlexibleSearchService().<CxSegmentModel> search(query.toString(),params);

        if (LOG.isDebugEnabled())
        {
            LOG.info("Results: " + (results == null ? "null" : String.valueOf(results.getCount())));
        }
         return CollectionUtils.isEmpty(results.getResult()) ? null : results.getResult(); // NOSONAR
    }
}
