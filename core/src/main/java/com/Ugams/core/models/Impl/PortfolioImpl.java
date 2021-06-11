package com.Ugams.core.models.Impl;


import com.Ugams.core.models.Portfolio;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class,
        adapters = Portfolio.class)
public class PortfolioImpl implements Portfolio{

    @Inject
    String portfolioText;

    @Inject
    String portfolioTitle;

    @Override
    public String getPortfolioTitle() {
        return portfolioText;
    }

    @Override
    public String getPortfolioText() {
        return portfolioTitle;
    }
}
