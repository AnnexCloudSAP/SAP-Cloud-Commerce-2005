package com.annexcloud.dao;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.personalizationservices.model.CxSegmentModel;
import de.hybris.platform.personalizationservices.model.CxUserToSegmentModel;
import de.hybris.platform.servicelayer.internal.dao.Dao;

import java.util.List;
import java.util.Map;

public interface AnnexCloudSegmentDao{
    List<CxSegmentModel> getUserToSegment(BaseSiteModel site);
}
