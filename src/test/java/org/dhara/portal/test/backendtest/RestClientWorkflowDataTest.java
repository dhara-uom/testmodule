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
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 11/13/13
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestClientWorkflowDataTest {
    private RestServiceConfig restServiceConfig;

    private RestClient restClient;

    @Before
    public void init() throws IOException, PortalException {
        setRestClient(new RestClient());
        restServiceConfig = new RestServiceConfig();
    }

    @Test
    public void getWorkflows() throws IOException, PortalException {

        List<WorkflowHelper> workflowHelperList = readResponse();

        boolean experimentDataReturned = false;
        if(workflowHelperList.size()>0){
            experimentDataReturned = true;
        }

        Assert.assertTrue(experimentDataReturned);
    }


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
