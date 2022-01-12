package com.ugams.core.models.impl;


import com.ugams.core.config.UgamOSGIConfig;
import com.ugams.core.models.OSGiConfigModel;
import com.ugams.core.services.impl.OSGIConfigImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class OSGiConfigModelImplTest {
    AemContext aemContext = new AemContext();
    OSGIConfigImpl osgiConfig;

    private String serviceName;
    private int serviceID;
    private boolean isService;
    private String[] countries;
    private String runModes;

    private String[] country={"us","in"};
    @BeforeEach
    void setUp() throws NoSuchFieldException {
        osgiConfig=aemContext.registerService(new OSGIConfigImpl());
        UgamOSGIConfig ugamOSGIConfig = mock(UgamOSGIConfig.class);
        when(ugamOSGIConfig.serviceName()).thenReturn("Demo Service");
        when(ugamOSGIConfig.serviceRunMode()).thenReturn("author");
        when(ugamOSGIConfig.serviceID()).thenReturn(0);
        when(ugamOSGIConfig.isService()).thenReturn(false);
        when(ugamOSGIConfig.serviceCountry()).thenReturn(country);
        osgiConfig.activate(ugamOSGIConfig);
    }

    @Test
    void getServiceName() {
        //Resource resource = aemContext.currentResource("/content");
        OSGiConfigModel osGiConfigModel = aemContext.request().adaptTo(OSGiConfigModel.class);
        assertEquals("Demo Service", osGiConfigModel.getServiceName());

    }

    @Test
    void getServiceID() {
        OSGiConfigModel osGiConfigModel = aemContext.request().adaptTo(OSGiConfigModel.class);
        assertEquals(0,osGiConfigModel.getServiceID());
    }

    @Test
    void getIsService() {
        OSGiConfigModel osGiConfigModel = aemContext.request().adaptTo(OSGiConfigModel.class);
        assertEquals(false,osGiConfigModel.getIsService());
    }

    @Test
    void getCountries() {
        OSGiConfigModel osGiConfigModel = aemContext.request().adaptTo(OSGiConfigModel.class);
        assertEquals(country,osGiConfigModel.getCountries());
    }

    @Test
    void getRunModes() {
        OSGiConfigModel osGiConfigModel = aemContext.request().adaptTo(OSGiConfigModel.class);
        assertEquals("author",osGiConfigModel.getRunModes());
    }
}