package com.Ugams.core.workflow;

import com.adobe.granite.workflow.exec.WorkflowData;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

import javax.jcr.Node;
import javax.jcr.Session;

@Component(
        service = WorkflowProcess.class,
        property = {"process.label" + " = Ugams Workflow Step"}
)
public class CustomWorkflow implements WorkflowProcess{

    private static final Logger log = LoggerFactory.getLogger(CustomWorkflow.class);


    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

        log.info("Executing the workflow");

        try {
            WorkflowData workflowData = workItem.getWorkflowData();
            if (workflowData.getPayloadType().equals("JCR_PATH")) {
                Session session = workflowSession.adaptTo(Session.class);
                String payloadpath = workflowData.getPayload().toString() + "/jcr:content";
                Node node = (Node) session.getItem(payloadpath);
                String[] process = metaDataMap.get("PROCESS_ARGS","string").toString().split(",");
                for (String pro : process) {
                    String [] arg=pro.split(":");
                    String property=arg[0];
                    String value=arg[1];
                    if(node!=null){
                        node.setProperty(property,value);
                    }
                }
            }
        }catch (Exception e){
            log.info("\n ERROR",e.getMessage());
        }
    }

}
