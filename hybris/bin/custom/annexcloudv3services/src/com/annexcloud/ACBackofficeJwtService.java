package com.annexcloud;

import com.annex.cloud.backoffice.jwt.ACBackofficeJwtRequest;
import com.annexcloud.model.AnnexCloudModel;

public interface ACBackofficeJwtService {
    public String backoffieTokenCreation(final ACBackofficeJwtRequest payload, final AnnexCloudModel annexCloudModel);

}
