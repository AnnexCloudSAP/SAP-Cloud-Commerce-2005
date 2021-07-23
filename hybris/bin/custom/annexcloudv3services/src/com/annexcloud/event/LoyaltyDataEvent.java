package com.annexcloud.event;

import com.annex.cloud.v3.api.response.user.ACUserDetailsResponse;
import de.hybris.platform.servicelayer.event.events.AbstractEvent;


public class LoyaltyDataEvent extends AbstractEvent {
    private ACUserDetailsResponse acUserDetailsResponse;

    public LoyaltyDataEvent(ACUserDetailsResponse acUserDetailsResponse)
    {
        super();
        this.acUserDetailsResponse = acUserDetailsResponse;
    }

    public ACUserDetailsResponse getAcUserDetailsResponse() {
        return acUserDetailsResponse;
    }

    public void setAcUserDetailsResponse(ACUserDetailsResponse acUserDetailsResponse) {
        this.acUserDetailsResponse = acUserDetailsResponse;
    }
}
