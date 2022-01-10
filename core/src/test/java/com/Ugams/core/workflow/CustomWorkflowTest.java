package com.Ugams.core.workflow;


import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class CustomWorkflowTest {

    AemContext aemContext = new AemContext(ResourceResolverType.JCR_MOCK);

    CustomWorkflow customWorkflow;

    @BeforeEach
    void setUp() {
        customWorkflow = aemContext.registerService(new CustomWorkflow());
    }

    @Test
    void execute() throws WorkflowException, NoSuchFieldException {
        WorkItem workItem = mock(WorkItem.class);
        WorkflowSession workflowSession = mock(WorkflowSession.class);
        MetaDataMap metaDataMap = mock(MetaDataMap.class);
        WorkflowData workflowData = mock(WorkflowData.class);
        CustomWorkflow customWorkflow1 = mock(CustomWorkflow.class);
        doNothing().when(customWorkflow1).execute(workItem,workflowSession,metaDataMap);
        //PrivateAccessor.setField(customWorkflow1,"workflowData",workflowData);
        lenient().when(workItem.getWorkflowData()).thenReturn(workflowData);

        customWorkflow1.execute(workItem,workflowSession,metaDataMap);
        //verify(customWorkflow1,times(1)).execute(workItem,workflowSession,metaDataMap);
        //assertAll(customWorkflow.execute(workItem,workflowSession,metaDataMap));
        //assertNotNull(customWorkflow1.execute(workItem,workflowSession,metaDataMap));
    }
}