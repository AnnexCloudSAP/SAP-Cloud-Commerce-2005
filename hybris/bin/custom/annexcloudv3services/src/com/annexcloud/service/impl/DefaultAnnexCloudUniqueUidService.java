package com.annexcloud.service.impl;

import com.annexcloud.AnnexCloudJWTService;
import com.annexcloud.model.AnnexCloudModel;
import com.annexcloud.service.AnnexCloudUniqueUidService;
import de.hybris.platform.commerceservices.order.CommerceCartCalculationStrategy;
import de.hybris.platform.commerceservices.strategies.CustomerNameStrategy;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.log4j.Logger;
public class DefaultAnnexCloudUniqueUidService implements AnnexCloudUniqueUidService {

    private static final Logger LOG = Logger.getLogger(DefaultAnnexCloudUniqueUidService.class);
    private BaseStoreService baseStoreService;

    /** The common I 18 N service. */
    private CommonI18NService commonI18NService;

    /** The model service. */
    private ModelService modelService;

    /** The annex cloud rest system service factory. */
    private DefaultAnnexCloudSystemServiceFactory annexCloudRestSystemServiceFactory;

    /** The session service. */
    private SessionService sessionService;

    /** The commerce cart calculation strategy. */
    private CommerceCartCalculationStrategy commerceCartCalculationStrategy;

    /** The customer name strategy. */
    private CustomerNameStrategy customerNameStrategy;

    /** The annex cloud JWT service. */
    private AnnexCloudJWTService annexCloudJWTService;

    private UserService userService;
    @Override
    public String getACUniqueId()
    {
        AnnexCloudModel annexCloudModel = (AnnexCloudModel)getBaseStoreService().getCurrentBaseStore().getAnnexCloud();
        UserModel userModel = userService.getCurrentUser();
        String userUid = userModel.getProperty(annexCloudModel.getAcPkType().getCode());
        return userUid;
    }

    @Override
    public String getACUniqueId(AnnexCloudModel annexCloudModel,UserModel userModel) {
        String userUid = userModel.getProperty(annexCloudModel.getAcPkType().getCode());
        return userUid;
    }
    public BaseStoreService getBaseStoreService() {
        return baseStoreService;
    }

    public void setBaseStoreService(BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }

    public CommonI18NService getCommonI18NService() {
        return commonI18NService;
    }

    public void setCommonI18NService(CommonI18NService commonI18NService) {
        this.commonI18NService = commonI18NService;
    }

    public ModelService getModelService() {
        return modelService;
    }

    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public DefaultAnnexCloudSystemServiceFactory getAnnexCloudRestSystemServiceFactory() {
        return annexCloudRestSystemServiceFactory;
    }

    public void setAnnexCloudRestSystemServiceFactory(DefaultAnnexCloudSystemServiceFactory annexCloudRestSystemServiceFactory) {
        this.annexCloudRestSystemServiceFactory = annexCloudRestSystemServiceFactory;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public CommerceCartCalculationStrategy getCommerceCartCalculationStrategy() {
        return commerceCartCalculationStrategy;
    }

    public void setCommerceCartCalculationStrategy(CommerceCartCalculationStrategy commerceCartCalculationStrategy) {
        this.commerceCartCalculationStrategy = commerceCartCalculationStrategy;
    }

    public CustomerNameStrategy getCustomerNameStrategy() {
        return customerNameStrategy;
    }

    public void setCustomerNameStrategy(CustomerNameStrategy customerNameStrategy) {
        this.customerNameStrategy = customerNameStrategy;
    }

    public AnnexCloudJWTService getAnnexCloudJWTService() {
        return annexCloudJWTService;
    }

    public void setAnnexCloudJWTService(AnnexCloudJWTService annexCloudJWTService) {
        this.annexCloudJWTService = annexCloudJWTService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
