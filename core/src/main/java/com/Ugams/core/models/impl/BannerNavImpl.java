package com.Ugams.core.models.impl;

import com.Ugams.core.models.BannerNav;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
        adapters = BannerNav.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BannerNavImpl implements BannerNav{

    @Inject
    String title;

    @Inject
    String nav1;

    @Inject
    String nav2;

    @Inject
    String nav1Path;

    @Inject
    String nav2Path;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getNav1() {
        return nav1;
    }

    @Override
    public String getNav2() {
        return nav2;
    }

    @Override
    public String getNav1Path() {
        return nav1Path;
    }

    @Override
    public String getNav2Path() {
        return nav2Path;
    }
}
