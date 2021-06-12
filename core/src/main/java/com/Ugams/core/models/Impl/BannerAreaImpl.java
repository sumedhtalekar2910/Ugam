package com.Ugams.core.models.Impl;

import com.Ugams.core.models.BannerArea;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
        adapters = BannerArea.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BannerAreaImpl implements BannerArea{

    @Inject
    String title;

    @Inject
    String desc;

    @Inject
    String heading;

    @Inject
    String buttonTitle;

    @Override
    public String getBannerAreaTitle() {
        return null;
    }

    @Override
    public String getBannerAreaHeading() {
        return null;
    }

    @Override
    public String getBannerAreaDescription() {
        return null;
    }

    @Override
    public String getBannerAreaButtonTitle() {
        return null;
    }
}
