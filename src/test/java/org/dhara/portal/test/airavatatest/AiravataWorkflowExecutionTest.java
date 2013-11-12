package org.dhara.portal.test.airavatatest;

import junit.framework.Assert;
import org.apache.airavata.registry.api.impl.WorkflowExecutionDataImpl;
import org.apache.airavata.registry.api.workflow.InputData;
import org.apache.airavata.registry.api.workflow.NodeExecutionData;
import org.apache.airavata.registry.api.workflow.OutputData;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 11/12/13
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class AiravataWorkflowExecutionTest {
    WorkflowManager workflowManager;
    @Before
    public void setUp() throws Exception {
        workflowManager=WorkflowManager.getInstance();
        workflowManager.uploadWorkflow();
    }

    @Test
    public void testRunWorkflow() throws Exception {
        String input = null;
        String output = null;
        for (WorkflowExecutionDataImpl executionDataImpl : workflowManager.runWorkflow()) {
            List<NodeExecutionData> nodeDataList = executionDataImpl.getNodeDataList();
            for (NodeExecutionData nodeExecutionData : nodeDataList) {
                List<InputData> inputData = nodeExecutionData.getInputData();
                for (InputData data : inputData) {
                    input=data.getValue();
                }
                List<OutputData> outputData = nodeExecutionData.getOutputData();
                for (OutputData data : outputData) {
                    output=data.getValue();
                }
            }
        }
        assertNotNull(input);
        assertNotNull(output);
        assertEquals(input, output);
    }


}
