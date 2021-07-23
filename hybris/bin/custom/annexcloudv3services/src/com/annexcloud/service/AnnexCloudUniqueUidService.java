package com.annexcloud.service;

import com.annexcloud.model.AnnexCloudModel;
import com.annexcloud.service.impl.DefaultAnnexCloudUniqueUidService;
import de.hybris.platform.core.model.user.UserModel;

public interface AnnexCloudUniqueUidService {
    String getACUniqueId();

    String getACUniqueId(AnnexCloudModel annexCloudModel, UserModel userModel);
}