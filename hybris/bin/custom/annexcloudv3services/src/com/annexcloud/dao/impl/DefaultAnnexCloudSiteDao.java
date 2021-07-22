package com.annexcloud.dao.impl;

import com.annexcloud.model.AnnexCloudModel;
import de.hybris.platform.personalizationservices.model.CxUserToSegmentModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;

public class DefaultAnnexCloudSiteDao extends DefaultGenericDao<AnnexCloudModel> {
    public DefaultAnnexCloudSiteDao(){
        super(AnnexCloudModel._TYPECODE);
    }
}
