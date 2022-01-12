package com.ugams.core.models.impl;

import com.ugams.core.models.OSGiConfigModel;
import com.ugams.core.services.OSGIConfig;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class,
        adapters = OSGiConfigModel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiConfigModelImpl implements OSGiConfigModel {

    @OSGiService
    OSGIConfig osgiConfig;

    @Override
    public String getServiceName() {
        return osgiConfig.getServiceName();
    }

    @Override
    public int getServiceID() {
        return osgiConfig.getServiceID();
    }

    @Override
    public boolean getIsService() {
        return osgiConfig.isService();
    }

    @Override
    public String[] getCountries() {
        return osgiConfig.getCountries();
    }

    @Override
    public String getRunModes() {
        return osgiConfig.getRunModes();
    }
}
