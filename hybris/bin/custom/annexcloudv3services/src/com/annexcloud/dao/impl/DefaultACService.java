package com.annexcloud.dao.impl;

import com.annexcloud.dao.ACService;
import com.annexcloud.model.AnnexCloudModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

import java.util.List;

public class DefaultACService extends DefaultGenericDao<AnnexCloudModel> implements ACService {

    public DefaultACService() {
        super(AnnexCloudModel._TYPECODE);
    }

    @Override
    public List<AnnexCloudModel> getSites() {
        return find();
    }
}
