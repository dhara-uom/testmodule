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
package org.dhara.portal.test.backendtest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.dhara.portal.test.exception.PortalException;
import org.dhara.portal.test.helper.WorkflowHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Test class for check workflow data
 */
public class RestClientWorkflowDataTest {
    private RestServiceConfig restServiceConfig;

    private RestClient restClient;

    @Before
    public void init() throws IOException, PortalException {
        setRestClient(new RestClient());
        restServiceConfig = new RestServiceConfig();
    }

    //Test for check get all workflows from backend webapps' rest service
    @Test
    public void getWorkflows() throws IOException, PortalException {

        List<WorkflowHelper> workflowHelperList = readResponse();

        boolean experimentDataReturned = false;
        if(workflowHelperList.size()>0){
            experimentDataReturned = true;
        }

        Assert.assertTrue(experimentDataReturned);
    }

    /**
     * Read REST service calls' response and transform it
     * @return
     * @throws IOException
     * @throws PortalException
     */
    public List<WorkflowHelper> readResponse() throws IOException, PortalException {
        String response= getRestClient().getResponse(getRestServiceConfig().getServerUrl() + RestResourceUtils.WORKFLOWDATA_RESOURCE);
        ObjectMapper mapper = new ObjectMapper();
        List<WorkflowHelper> workflowHelperList = mapper.readValue(response, new TypeReference<List<WorkflowHelper>>(){});
        return workflowHelperList;
    }


    public RestClient getRestClient() {
        return restClient;
    }

    public RestServiceConfig getRestServiceConfig() {
        return restServiceConfig;
    }

    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }
}
