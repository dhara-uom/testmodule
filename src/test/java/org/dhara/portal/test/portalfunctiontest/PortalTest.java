package org.dhara.portal.test.portalfunctiontest;

import org.dhara.portal.test.exception.PortalException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 11/15/13
 * Time: 2:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class PortalTest {
    PortalRestClient portalRestClient;

    @Before
    public void init() throws PortalException {
        portalRestClient=new PortalRestClient();
    }

    @Test
    public void  getWorkflowsTest() {
        String response=portalRestClient.getResponse("/app/admin/workflows");
        assertNotNull(response);
    }

    @Test
    public void getExperimentsTest() {
        String response=portalRestClient.getResponse("/app/admin/experiments");
        assertNotNull(response);
    }

    @Test
    public void deployWorkflowTest() {
        String workflowId="EchoWorkflow";
        String response=portalRestClient.getResponse("/app/admin/workflow/deploy?workflowId="+workflowId);
        assertNotNull(response);

    }

}
