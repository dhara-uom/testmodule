package org.dhara.portal.test.backendtest;

import junit.framework.Assert;
import org.dhara.portal.test.exception.PortalException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 11/15/13
 * Time: 7:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackendWorkflowExecutionTest {
    private RestServiceConfig restServiceConfig;

    private RestClient restClient;

    @Before
    public void init() throws IOException, PortalException {
        setRestClient(new RestClient());
        restServiceConfig = new RestServiceConfig();
    }

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
