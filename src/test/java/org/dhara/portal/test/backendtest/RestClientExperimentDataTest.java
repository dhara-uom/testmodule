package org.dhara.portal.test.backendtest;


import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.dhara.portal.test.exception.PortalException;
import org.dhara.portal.test.helper.ExperimentHelper;
import org.dhara.portal.test.helper.Nodehelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 11/13/13
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestClientExperimentDataTest {

    private RestServiceConfig restServiceConfig;

    private RestClient restClient;

    @Before
    public void init() throws IOException, PortalException {
        setRestClient(new RestClient());
        restServiceConfig = new RestServiceConfig();
    }

    @Test
    public void a_getExperimentData() throws IOException, PortalException {

        List<ExperimentHelper> experimentHelperList = readResponse();

        boolean experimentDataReturned = false;
        if(experimentHelperList.size()>0){
            experimentDataReturned = true;
        }

        Assert.assertTrue(experimentDataReturned);
    }

    @Test
    public void a_getExperimentNodeData() throws IOException, PortalException {

        List<Nodehelper> nodehelperList = readResponse().get(0).getNodehelperList();

        boolean experimentNodeDataReturned = false;
        if(nodehelperList.size()>0){
            experimentNodeDataReturned = true;
        }

        Assert.assertTrue(experimentNodeDataReturned);
    }

    public List<ExperimentHelper> readResponse() throws IOException, PortalException {
        String response= getRestClient().getResponse(getRestServiceConfig().getServerUrl() + RestResourceUtils.EXPERIMENTDATA_RESOURCE);
        ObjectMapper mapper = new ObjectMapper();
        List<ExperimentHelper> experimentHelperList = mapper.readValue(response, new TypeReference<List<ExperimentHelper>>(){});
        return experimentHelperList;
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
