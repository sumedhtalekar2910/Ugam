package com.Ugams.core.schedulers;

import com.Ugams.core.config.SchedulerConfig;
import com.Ugams.core.services.CurrentDate;
import io.wcm.testing.mock.aem.junit5.AemContext;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomSchedulerTest {

  /*  AemContext aemContext = new AemContext(ResourceResolverType.JCR_MOCK);
    CustomScheduler customScheduler;
    CurrentDate currentDate;

    private int schedulerId;

    @BeforeEach
    void setUp() {
        customScheduler = aemContext.registerService(new CustomScheduler());

    }

    @Test
    void activate() {
       SchedulerConfig config = mock(SchedulerConfig.class);
       schedulerId = 12345;
       when(config.schedulerName().hashCode()).thenReturn(schedulerId);
       //PrivateAccessor.setField(customScheduler,"schedulerId",schedulerId);
       customScheduler.activate(config);
    }
    @Test
    void deactivate() {
        SchedulerConfig config = mock(SchedulerConfig.class);
        //lenient().when(config.schedulerName().hashCode()).thenReturn(1234);
        customScheduler.deactivate(config);
    }*/

  /*  @Test
    void run() throws NoSuchFieldException {
        String path = "/content/ugams/us/en/demo/jcr:content/root/container/date";
        PrivateAccessor.setField(currentDate,"path",path);
        customScheduler.run();
    }*/
}