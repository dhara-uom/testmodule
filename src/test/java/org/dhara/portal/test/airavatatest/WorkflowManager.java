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

import org.apache.airavata.client.AiravataAPIFactory;
import org.apache.airavata.client.api.AiravataAPI;
import org.apache.airavata.client.api.AiravataAPIInvocationException;
import org.apache.airavata.client.api.DescriptorRecordAlreadyExistsException;
import org.apache.airavata.registry.api.PasswordCallback;
import org.apache.airavata.registry.api.exception.worker.ExperimentLazyLoadedException;
import org.apache.airavata.registry.api.impl.WorkflowExecutionDataImpl;
import org.apache.airavata.registry.api.workflow.ExperimentData;
import org.apache.airavata.registry.api.workflow.NodeExecutionData;
import org.apache.airavata.rest.client.PasswordCallbackImpl;
import org.apache.airavata.workflow.model.wf.Workflow;
import org.apache.airavata.workflow.model.wf.WorkflowData;
import org.apache.airavata.workflow.model.wf.WorkflowInput;
import org.dhara.portal.test.exception.PortalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton test helper class for check functionality of hosted Apache Airavata through its' API
 */
public class WorkflowManager {

    private static final Logger log = LoggerFactory.getLogger(WorkflowManager.class);

    private static int port;
    private static String serverUrl;
    private static String serverContextName;

    private static String registryURL;

    private static String gatewayName;
    private static String userName;
    private static String password;

    private static String input;
    private static String output;
    private static AiravataAPI airavataAPI;
    private PasswordCallback passwordCallback;
    String workflowName = "EchoWorkflow";

    private String experimentId;
    private static WorkflowManager workflowManager;

    public static WorkflowManager getInstance() throws PortalException, AiravataAPIInvocationException, URISyntaxException {
       if(workflowManager==null) {
           return new WorkflowManager();
       } else {
           return workflowManager;
       }
    }

    //Create Apache Airavata API object from configuration
    private WorkflowManager() throws PortalException, URISyntaxException, AiravataAPIInvocationException {
        AiravataConfig airavataConfig=new AiravataConfig();
        // creating airavata client object //
        port = airavataConfig.getPort();
        serverUrl = airavataConfig.getServerUrl();
        serverContextName = airavataConfig.getServerContextName();
        gatewayName = airavataConfig.getGatewayName();
        userName = airavataConfig.getUserName();
        password = airavataConfig.getPassword();
        registryURL = "http://" + serverUrl + ":" + port + "/" + serverContextName + "/api";
        passwordCallback = new PasswordCallbackImpl(getUserName(), getPassword());
        airavataAPI = AiravataAPIFactory.getAPI(new URI(getRegistryURL()), getGatewayName(), getUserName(),
                passwordCallback);
    }

    /**
     * Run workflow through Apache Airavata client API
     * @return
     * @throws AiravataAPIInvocationException
     * @throws IOException
     * @throws URISyntaxException
     * @throws ExperimentLazyLoadedException
     * @throws PortalException
     * @throws DescriptorRecordAlreadyExistsException
     */
    public List<WorkflowExecutionDataImpl> runWorkflow() throws AiravataAPIInvocationException, IOException, URISyntaxException,
            ExperimentLazyLoadedException, PortalException, DescriptorRecordAlreadyExistsException {
        // Now workflow has saved, Now we have to set inputs
        List<WorkflowInput> workflowInputs = new ArrayList<WorkflowInput>();
        String name = "Input";
        String type = "String";
        String value = "Output=echo";
        WorkflowInput workflowInput = new WorkflowInput(name, (type == null || type.isEmpty()) ? "String" : type, null,
                value, false);
        workflowInputs.add(workflowInput);
        // Now inputs are set properly to the workflow, now we are about to run the workflow(submit the workflow run to
        // intepreterService)
        setExperimentId(airavataAPI.getExecutionManager().runExperiment(workflowName, workflowInputs));
        MonitorWorkflow.monitor(getExperimentId());
        airavataAPI.getExecutionManager().waitForExperimentTermination(getExperimentId());
        ExperimentData experimentData = airavataAPI.getProvenanceManager().getExperimentData(getExperimentId());
        List<WorkflowExecutionDataImpl> workflowInstanceData = experimentData.getWorkflowExecutionDataList();
        return workflowInstanceData;
    }

    public static String getRegistryURL() {
        return registryURL;
    }

    public static String getGatewayName() {
        return gatewayName;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    /**
     * Upload a new dummy workflow to Airavata if Echo workflow not exists
     * @throws AiravataAPIInvocationException
     * @throws IOException
     * @throws DescriptorRecordAlreadyExistsException
     */
    public void uploadWorkflow() throws AiravataAPIInvocationException, IOException, DescriptorRecordAlreadyExistsException {
        // Saving workflow method, workflow file has the workflow Name set to EchoSample, so when we use saveWorkflow
        // method it will
        // save the workflow with that name.
        boolean isWorkflowExist=false;
        for(WorkflowData workflowData:airavataAPI.getWorkflowManager().getAllWorkflows()) {
            if(workflowData.getName().equalsIgnoreCase(workflowName)){
                isWorkflowExist=true;
                break;
            }
        }
        if(isWorkflowExist){
            airavataAPI.getWorkflowManager().deleteWorkflow("EchoWorkflow");
        }
        airavataAPI.getWorkflowManager().addWorkflow(getWorkflowComposeContent());
    }

    /**
     * Get dummy workflow details
     * @return
     * @throws IOException
     */
    protected static String getWorkflowComposeContent() throws IOException {
        System.out.println((new File(".")).getAbsolutePath());
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/EchoWorkflow.xwf"));
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }


    /**
     * Get all workflows through Apache Airavata's client API
     * @return
     * @throws AiravataAPIInvocationException
     */
    public List<Workflow> getAllWorkflows() throws AiravataAPIInvocationException {
        return airavataAPI.getWorkflowManager().getWorkflows();
    }

    /**
     * Get experiment data through Apache AIravata's client API
     * @return
     * @throws PortalException
     * @throws AiravataAPIInvocationException
     */
    public List<ExperimentData> getExperimentData() throws PortalException, AiravataAPIInvocationException {
        List<ExperimentData> experimentByUser = airavataAPI.getProvenanceManager().getExperimentDataList();
        return experimentByUser;
    }

    /**
     * Get specified workflow through Apache Airavata's client API
     * @param identifier
     * @return
     * @throws PortalException
     */
    public Workflow getWorkflow(String identifier) throws PortalException {
        Workflow workflow = null;
        org.apache.airavata.client.api.WorkflowManager workflowManager=  airavataAPI.getWorkflowManager();
        try {
            workflow=workflowManager.getWorkflow(identifier);
        } catch (AiravataAPIInvocationException e) {
            e.printStackTrace();
        }
        return workflow;
    }

    /**
     * Get experiment data through Apache Airavata's client API
     * @param experimentId
     * @return
     * @throws PortalException
     * @throws AiravataAPIInvocationException
     * @throws ExperimentLazyLoadedException
     */
    public List<NodeExecutionData> getWorkflowExperimentData(String experimentId) throws PortalException, AiravataAPIInvocationException,
            ExperimentLazyLoadedException {
        ExperimentData data = airavataAPI.getProvenanceManager().getExperimentData(experimentId);
        List<NodeExecutionData> nodeData = data.getNodeDataList();
        return nodeData;
    }

    public String getExperimentId() {
        return experimentId;
    }

    public void setExperimentId(String experimentId) {
        if(experimentId!=null){
        this.experimentId = experimentId;
        }
    }
}
