package org.dhara.portal.test.backendtest;

import org.dhara.portal.test.exception.PortalException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Test class for check functionality of temporary backend rest service to access Apache Airavata
 */
public class BackendWorkflowExecutionTest {
    private RestServiceConfig restServiceConfig;

    private RestClient restClient;

    //Initialization
    @Before
    public void init() throws IOException, PortalException {
        setRestClient(new RestClient());
        restServiceConfig = new RestServiceConfig();
    }

    //Execute a workflow through backend web application
    @Test
    public void executeWorkflowTest() {
        String workflowId="EchoWorkflow";
        String input="echo";
        String response= restClient.getResponse(restServiceConfig.getServerUrl()+"/connect/ExecutionServlet?Input="+input
                +"&workflowId="+workflowId);
        assertNotNull(response);
    }

    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }
}
