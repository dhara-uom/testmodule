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
import org.apache.airavata.registry.api.PasswordCallback;
import org.apache.airavata.rest.client.PasswordCallbackImpl;
import org.apache.airavata.ws.monitor.Monitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class MonitorWorkflow {
    private static final Logger log = LoggerFactory.getLogger(MonitorWorkflow.class);

        public static void monitor(final String experimentId) throws AiravataAPIInvocationException, URISyntaxException {
        PasswordCallback passwordCallback = new PasswordCallbackImpl(WorkflowManager.getUserName(),
                WorkflowManager.getPassword());
        AiravataAPI airavataAPI = AiravataAPIFactory.getAPI(new URI(WorkflowManager.getRegistryURL()),
                WorkflowManager.getGatewayName(), WorkflowManager.getUserName(), passwordCallback);
        MonitorListener monitorListener = new MonitorListener();
        Monitor experimentMonitor = airavataAPI.getExecutionManager().getExperimentMonitor(experimentId,
                monitorListener);
        log.info("Started the Workflow monitor");
        experimentMonitor.startMonitoring();
    }
}
