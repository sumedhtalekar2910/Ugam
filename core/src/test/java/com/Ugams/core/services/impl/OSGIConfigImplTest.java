package com.Ugams.core.services.impl;

import com.Ugams.core.config.UgamOSGIConfig;
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
class OSGIConfigImplTest {

    AemContext aemContext = new AemContext();
    OSGIConfigImpl osgiConfig;

    private String serviceName;
    private int serviceID;
    private boolean isService;
    private String[] countries;
    private String runModes;

    private String[] country={"us","in"};

    @BeforeEach
    void setUp() {
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
    void activate() {
    }

    @Test
    void getServiceName() {
        assertEquals("Demo Service",osgiConfig.getServiceName());
    }

    @Test
    void getServiceID() {
        assertEquals(0,osgiConfig.getServiceID());
    }

    @Test
    void isService() {
        assertEquals(false,osgiConfig.isService());
    }

    @Test
    void getCountries() {
        assertEquals(country,osgiConfig.getCountries());
    }

    @Test
    void getRunModes() {
        assertEquals("author",osgiConfig.getRunModes());
    }
}