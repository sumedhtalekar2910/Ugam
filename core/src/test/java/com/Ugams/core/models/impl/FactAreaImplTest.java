package com.Ugams.core.models.impl;

import com.Ugams.core.models.Brands;
import com.Ugams.core.models.FactArea;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class FactAreaImplTest {

    private final AemContext aemContext = new AemContext();

    @BeforeEach
    void setUp() {
        aemContext.load().json("/factarea.json","/content");
    }

    @Test
    void getFactAreaDetails() {
        Resource resource = aemContext.currentResource("/content/factarea");
        FactArea factArea = resource.adaptTo(FactArea.class);
        assertEquals("Projects Completed", factArea.getFactAreaDetails().get(0).get("factText"));
        assertEquals("2536", factArea.getFactAreaDetails().get(0).get("factNumber"));
    }
}