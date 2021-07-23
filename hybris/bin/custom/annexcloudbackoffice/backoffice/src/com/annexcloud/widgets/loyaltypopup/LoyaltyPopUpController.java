package com.annexcloud.widgets.loyaltypopup;

import com.annex.cloud.backoffice.jwt.ACBackofficeJwtRequest;
import com.annexcloud.ACBackofficeJwtService;
import com.annexcloud.dao.ACService;
import com.annexcloud.model.AnnexCloudModel;
import com.hybris.cockpitng.annotations.SocketEvent;
import com.hybris.cockpitng.annotations.ViewEvent;
import com.hybris.cockpitng.util.DefaultWidgetController;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.user.UserService;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Iframe;

import java.util.List;

public class LoyaltyPopUpController extends DefaultWidgetController
{
    @Wire
    private Combobox combobox;

    @WireVariable
    UserService userService;

    @WireVariable
    ACBackofficeJwtService backofficeJWTService;

    @WireVariable
    ACService acService;

    private Iframe iframe;

    List<AnnexCloudModel> sites;
    CustomerModel customer;
    @SocketEvent(socketId = "openACWidget")
    public void initializeWithContext(final CustomerModel data)
    {   customer=data;
        sites = acService.getSites();
        combobox.getItems().clear();
        for(AnnexCloudModel ac:sites) {
            combobox.appendItem(ac.getCode());
         }
    }

    @ViewEvent(componentID = "combobox", eventName = Events.ON_SELECT)
    public void onSelectGroup(Event e){
        AnnexCloudModel acSite =  sites.stream().filter(site->site.getCode().equals(combobox.getValue())).findFirst().orElse(null);
        UserModel user = userService.getCurrentUser();
        if(user != null && acSite!=null)
        {
            ACBackofficeJwtRequest a = new ACBackofficeJwtRequest();
            a.setRole(user.getGroups().stream().findAny().get().getUid());
            a.setSiteId(acSite.getSiteId());
            a.setUserId(customer.getUid());
            a.setSiteName(acSite.getCode());
            String token = backofficeJWTService.backoffieTokenCreation(a,acSite);
            iframe.setSrc("https://api.socialannex.com/sapbo/v3/loyaltydetail.php?param="+token+"&site_id="+acSite.getSiteId());
        }
    }
}