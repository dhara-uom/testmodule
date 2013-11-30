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
package org.dhara.portal.test.portalfunctiontest;

import org.dhara.portal.test.exception.PortalException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The test class
 */
public class PortalTest {
    PortalRestClient portalRestClient;

    @Before
    public void init() throws PortalException {
        portalRestClient=new PortalRestClient();
    }

    //Test for check whether workflows are listing fine in portal side
    @Test
    public void  getWorkflowsTest() {
        String response=portalRestClient.getResponse("/app/admin/workflows");
        Assert.assertTrue(response.contains("Workflows"));
    }

    //Test for check whether experiments are listing fine in portal side
    @Test
    public void getExperimentsTest() {
        String response=portalRestClient.getResponse("/app/admin/experiments");
        assertTrue(response.contains("Experiments"));
    }

    //Test for workflow deployment through portal
    @Test
    public void deployWorkflowTest() {
        String workflowId="EchoWorkflow";
        String response=portalRestClient.getResponse("/app/admin/workflow/deploy?workflowId="+workflowId);
        assertNotNull(response.contains("Workflows"));

    }

}
