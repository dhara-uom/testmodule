/***********************************************************************************************************************
 *
 * Dhara- A Geoscience Gateway
 * ==========================================
 *
 * Copyright (C) 2013 by Dhara
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************/
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
 * The Apache Airavatas' function test class
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AiravataWorkflowExecutionTest {

    WorkflowManager workflowManager;
    @Before
    public void setUp() throws Exception {
        workflowManager=WorkflowManager.getInstance();
        workflowManager.uploadWorkflow();
    }

    //Test method for check the functionality of get a specified workflow(echo workflow) through Apache Airavata API
    @Test
    public void a_getWorkflow() throws Exception {
        Workflow workflow=workflowManager.getWorkflow("EchoWorkflow");
        assertNotNull(workflow);
    }

    //Test method for check the functionality of run specified workflow and check the execution with received results (Echo response)
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

    //Test method for check the functionality get all workflows through Apache Airavata API
    @Test
    public void c_getAllWorkflows() throws Exception {
        List<Workflow> workflows=workflowManager.getAllWorkflows();
        boolean workflowsCount=false;
        if(workflows.size()>0) {
            workflowsCount=true;
        }
        assertTrue(workflowsCount);
    }

    //Test method for check the functionality of get experiment data of a workflow execution
    @Test
    public void d_getExperimentData() throws Exception {
        List<ExperimentData>  experimentDatas=workflowManager.getExperimentData();
        boolean experimentsCount=false;
        if(experimentDatas.size()>0) {
            experimentsCount=true;
        }
        assertTrue(experimentsCount);
    }

    //Test method for check the functionality get node execution data of a executed workflow
    @Test
    public void e_getNodeExecutionData() throws Exception {
        workflowManager.runWorkflow();
        List<NodeExecutionData> nodeExecutionDatas=workflowManager.getWorkflowExperimentData(workflowManager.getExperimentId());
        boolean nodeExecutionDataCount=false;
        if(nodeExecutionDatas.size()>0) {
            nodeExecutionDataCount=true;
        }
        assertTrue(nodeExecutionDataCount);
    }

}
