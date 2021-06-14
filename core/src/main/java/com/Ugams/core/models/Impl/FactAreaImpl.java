package com.Ugams.core.models.Impl;


import com.Ugams.core.models.FactArea;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Model(adaptables = Resource.class,
        adapters = FactArea.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FactAreaImpl implements FactArea{


    @Inject
    Resource resource;

    @Override
    public List<Map<String, String>> getFactAreaDetails() {
        List<Map<String, String>> factAreaMap = new ArrayList<>();
        Resource factArea = resource.getChild("bookdetailswithmap");
            if (factArea != null) {
                for (Resource fact : factArea.getChildren()) {
                    Map<String, String> factMap = new HashMap<>();
                    factMap.put("factNumber", fact.getValueMap().get("factNumber", String.class));
                    factMap.put("factText", fact.getValueMap().get("factText", String.class));
                    factAreaMap.add(factMap);
                }
            }
        return factAreaMap;
    }

}
