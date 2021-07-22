package com.annexcloud.actions;

import com.hybris.cockpitng.actions.ActionContext;
import com.hybris.cockpitng.actions.ActionResult;
import com.hybris.cockpitng.actions.CockpitAction;
import com.hybris.cockpitng.engine.impl.AbstractComponentWidgetAdapterAware;
import de.hybris.platform.core.model.user.CustomerModel;

public class LoyaltyAction extends AbstractComponentWidgetAdapterAware implements CockpitAction<CustomerModel, Object> {
    public static final String OPEN_WIDGET = "openACWidget";

    public LoyaltyAction() {
    }

    public ActionResult<Object> perform(ActionContext<CustomerModel> actionContext) {
        this.sendOutput(OPEN_WIDGET, actionContext.getData());
        return new ActionResult("success");
    }
}