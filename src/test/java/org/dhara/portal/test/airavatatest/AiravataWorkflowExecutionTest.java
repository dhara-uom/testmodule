package org.dhara.portal.test.airavatatest;

import org.apache.airavata.registry.api.impl.WorkflowExecutionDataImpl;
import org.apache.airavata.registry.api.workflow.ExperimentData;
import org.apache.airavata.registry.api.workflow.InputData;
import org.apache.airavata.registry.api.workflow.NodeExecutionData;
import org.apache.airavata.registry.api.workflow.OutputData;
import org.apache.airavata.workflow.model.wf.Workflow;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 11/12/13
 * Time: 1:58 PM
 * To change this template use File | Settings | File Templates.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AiravataWorkflowExecutionTest {

    WorkflowManager workflowManager;
    @Before
    public void setUp() throws Exception {
        workflowManager=WorkflowManager.getInstance();
        workflowManager.uploadWorkflow();
    }

    @Test
    public void a_getWorkflow() throws Exception {
        Workflow workflow=workflowManager.getWorkflow("EchoWorkflow");
        assertNotNull(workflow);
    }

    @Test
    public void b_testRunWorkflow() throws Exception {
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

    @Test
    public void c_getAllWorkflows() throws Exception {
        List<Workflow> workflows=workflowManager.getAllWorkflows();
        boolean workflowsCount=false;
        if(workflows.size()>0) {
            workflowsCount=true;
        }
        assertTrue(workflowsCount);
    }

    @Test
    public void d_getExperimentData() throws Exception {
        List<ExperimentData>  experimentDatas=workflowManager.getExperimentData();
        boolean experimentsCount=false;
        if(experimentDatas.size()>0) {
            experimentsCount=true;
        }
        assertTrue(experimentsCount);
    }

    @Test
    public void e_getNodeExecutionData() throws Exception {
        List<NodeExecutionData> nodeExecutionDatas=workflowManager.getWorkflowExperimentData(workflowManager.getExperimentId());
        boolean nodeExecutionDataCount=false;
        if(nodeExecutionDatas.size()>0) {
            nodeExecutionDataCount=true;
        }
        assertTrue(nodeExecutionDataCount);
    }

}
