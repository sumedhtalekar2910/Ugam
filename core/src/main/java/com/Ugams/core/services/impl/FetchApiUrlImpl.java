package com.ugams.core.services.impl;

import com.ugams.core.config.FetchApiConfig;
import com.ugams.core.services.FetchApiUrl;
import com.ugams.core.services.OSGIConfig;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = FetchApiUrl.class,immediate = true)
@Designate(ocd = FetchApiConfig.class )
public class FetchApiUrlImpl implements FetchApiUrl {

    String singleuserurl;
    String multiuserurl;

    @Activate
    public void activate(FetchApiConfig fetchApiConfig){
        singleuserurl = fetchApiConfig.SingleUserUrl();
        multiuserurl= fetchApiConfig.MultiUserUrl();
    }

    @Override
    public String getSingleUserUrl() {
        return singleuserurl;
    }

    @Override
    public String getMultiUserUrl() {
        return multiuserurl;
    }
}
