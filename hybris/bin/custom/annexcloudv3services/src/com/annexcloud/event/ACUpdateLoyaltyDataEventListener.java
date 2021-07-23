package com.annexcloud.event;

import com.annex.cloud.v3.api.response.user.ACUserDetailsResponse;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import org.apache.log4j.Logger;


public class ACUpdateLoyaltyDataEventListener extends AbstractEventListener<LoyaltyDataEvent> {
    // extends ApplicationEvent
    private static final Logger LOG = Logger.getLogger(ACUpdateLoyaltyDataEventListener.class);
    /** The model service. */
    private ModelService modelService;

    /** The business process service. */
    private BusinessProcessService businessProcessService;

    /** The annex cloud loyalty service. */
    private UserService userService;

    /** The annex cloud loyalty service. */

    @Override
    protected void onEvent(LoyaltyDataEvent loyaltyDataEvent) {
        ACUserDetailsResponse acUserDetailsResponse   = loyaltyDataEvent.getAcUserDetailsResponse();
        CustomerModel cModel = (CustomerModel) userService.getUserForUID(acUserDetailsResponse.getId());
        cModel.setAcId(acUserDetailsResponse.getId());
        cModel.setOptInDate(acUserDetailsResponse.getOptInDate());
        cModel.setOptInStatus(acUserDetailsResponse.getOptInStatus());
        modelService.save(cModel);
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
