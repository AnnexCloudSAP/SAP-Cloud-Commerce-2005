package com.annexcloud.customer.dao.Impl;

import com.annexcloud.customer.dao.ACCustomerDao;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.internal.dao.AbstractItemDao;
import org.apache.log4j.Logger;
import de.hybris.platform.servicelayer.search.SearchResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ACCustomerDaoImpl extends AbstractItemDao implements ACCustomerDao {
    private static final Logger LOG = Logger.getLogger(ACCustomerDaoImpl.class);

    private static final String GET_ALL_CUSTOMER_DETAILS = "SELECT {" + CustomerModel.PK + "} FROM {" + CustomerModel._TYPECODE + "} WHERE {" + CustomerModel.UID + "} != ?uid";

    public List<CustomerModel> getCustomerDetails()
    {
        final Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("uid", "anonymous");
        final SearchResult<CustomerModel> result = getFlexibleSearchService().search(GET_ALL_CUSTOMER_DETAILS,queryParams);
        return result.getResult();
    }
}
