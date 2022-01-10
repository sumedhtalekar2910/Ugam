package com.Ugams.core.models.impl;

import com.Ugams.core.config.UgamCAconfig;
import com.adobe.xfa.ut.StringUtils;
import com.day.cq.wcm.api.Page;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.caconfig.ConfigurationBuilder;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class CaconfigImplTest {

    AemContext aemContext = new AemContext(ResourceResolverType.JCR_MOCK);
    CaconfigImpl caconfig;
    Page currentPage ;
    ResourceResolver resourceResolver = mock(ResourceResolver.class);
    Resource contentResource = mock(Resource.class);
    ConfigurationBuilder configurationBuilder = mock(ConfigurationBuilder.class);


    private String siteCountry;
    private String siteAdmin;
    private String siteSection;

    @BeforeEach
    void setUp() throws NoSuchFieldException {
        caconfig=aemContext.registerService(new CaconfigImpl());
        UgamCAconfig caConfig = mock(UgamCAconfig.class);
        lenient().when(caConfig.siteAdmin()).thenReturn("ugam");
        lenient().when(caConfig.siteCountry()).thenReturn("us");
        lenient().when(caConfig.siteSection()).thenReturn("Site Section");

        currentPage = aemContext.create().page("/content/ugams/us/en");
        String currentPath = "/content/ugams/us/en";
        //Resource contentResource = resourceResolver.getResource(currentPath);
        PrivateAccessor.setField(caconfig,"currentPage",currentPage);
        PrivateAccessor.setField(caconfig,"resourceResolver",resourceResolver);
        when(resourceResolver.getResource(currentPath)).thenReturn(contentResource);
        when(contentResource.adaptTo(ConfigurationBuilder.class)).thenReturn(configurationBuilder);
        //PrivateAccessor.setField(caConfig,"contentResource",contentResource);
        when(configurationBuilder.as(UgamCAconfig.class)).thenReturn(caConfig);
        caconfig.postConstruct();
    }

    @Test
    void getSiteCountry() {
        assertEquals("us",caconfig.getSiteCountry());
    }

    @Test
    void getSiteAdmin() {
        assertEquals("ugam",caconfig.getSiteAdmin());
    }

    @Test
    void getSiteSection() {
        assertEquals("Site Section",caconfig.getSiteSection());
    }
}