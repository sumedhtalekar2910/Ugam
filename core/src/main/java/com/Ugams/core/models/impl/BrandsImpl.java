package com.ugams.core.models.impl;

import com.ugams.core.models.Brands;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;


import javax.inject.Inject;
import java.util.*;

@Model(adaptables = Resource.class,
        adapters = Brands.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BrandsImpl implements Brands{

    @Inject
    List<String> imgPath;

    @Override
    public List<String> getImgPath() {
        if(imgPath!=null)
        {
            return new ArrayList<>(imgPath);
        }
        else
        {
            return Collections.emptyList();
        }
    }

}
