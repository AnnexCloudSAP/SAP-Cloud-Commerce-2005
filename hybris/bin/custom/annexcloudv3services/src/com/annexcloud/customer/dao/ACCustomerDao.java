package com.annexcloud.customer.dao;

import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public interface ACCustomerDao {
    List<CustomerModel> getCustomerDetails();
}
