package com.ugams.core.services.impl;

import com.ugams.core.services.CurrentDate;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class CurrentDateImplTest {

    private final AemContext aemContext = new AemContext(ResourceResolverType.JCR_MOCK);

    CurrentDateImpl currentDate;
    String path = "/content/ugams/us/en/demo/jcr:content/root/container/date";

    @BeforeEach
    void setUp() {
        //currentDate=aemContext.registerService(new CurrentDateImpl());
        aemContext.addModelsForClasses(CurrentDate.class);
        aemContext.build().resource("/content/ugams/us/en/demo/jcr:content/root/container/date","Time" ,"2021-12-28T14:23:20.006Z");
        currentDate=mock(CurrentDateImpl.class);

    }

    @Test
    void updateDate() throws NoSuchFieldException {
        //currentDate=aemContext.request().adaptTo(CurrentDateImpl.class);
        //aemContext.build().resource("/content/ugams/us/en/demo/jcr:content/root/container/date","Time" ,"2021-12-28T14:23:20.006Z");
        //aemContext.currentResource("/content/ugams/us/en/demo/jcr:content/root/container/date");

        //PrivateAccessor.setField(currentDate,"node",node);
        //CurrentDate currentDate1 = aemContext.registerService(new CurrentDate(););
        //assertEquals(path,currentDate.UpdateDate(path));
        //doNothing().when(currentDate).UpdateDate(path);

        currentDate.updateDate(path);
        Mockito.verify(currentDate,times(1)).updateDate(path);

    }
}