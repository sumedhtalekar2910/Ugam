package com.Ugams.core.models.impl;

import com.Ugams.core.models.BannerNav;
import com.Ugams.core.models.HomeAbout;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class HomeAboutImplTest {

    private final AemContext aemContext = new AemContext();

    @BeforeEach
    void setUp() {
        aemContext.load().json("/homeabout.json","/content");
    }

    @Test
    void getHomeAboutTitle() {
        Resource resource = aemContext.currentResource("/content");
        HomeAbout homeAbout = resource.adaptTo(HomeAbout.class);
        assertEquals("title", homeAbout.getHomeAboutTitle());
    }

    @Test
    void getHomeAboutHeading() {
        Resource resource = aemContext.currentResource("/content");
        HomeAbout homeAbout = resource.adaptTo(HomeAbout.class);
        assertEquals("heading", homeAbout.getHomeAboutHeading());
    }

    @Test
    void getHomeAboutDescription() {
        Resource resource = aemContext.currentResource("/content");
        HomeAbout homeAbout = resource.adaptTo(HomeAbout.class);
        assertEquals("desc", homeAbout.getHomeAboutDescription());
    }

    @Test
    void getHomeAboutButtonTitle() {
        Resource resource = aemContext.currentResource("/content");
        HomeAbout homeAbout = resource.adaptTo(HomeAbout.class);
        assertEquals("buttonTitle", homeAbout.getHomeAboutButtonTitle());
    }

    @Test
    void getImg() {
        Resource resource = aemContext.currentResource("/content");
        HomeAbout homeAbout = resource.adaptTo(HomeAbout.class);
        assertEquals("img", homeAbout.getImg());
    }

    @Test
    void getPathValue() {
        Resource resource = aemContext.currentResource("/content");
        HomeAbout homeAbout = resource.adaptTo(HomeAbout.class);
        assertEquals("/home/about", homeAbout.getPathValue());
    }

}