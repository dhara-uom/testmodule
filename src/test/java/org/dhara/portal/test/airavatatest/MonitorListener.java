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

import org.apache.airavata.ws.monitor.EventData;
import org.apache.airavata.ws.monitor.EventDataListener;
import org.apache.airavata.ws.monitor.EventDataRepository;
import org.apache.airavata.ws.monitor.Monitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class relates to workflow execution
 */
public class MonitorListener implements EventDataListener {

    private static final Logger log = LoggerFactory.getLogger(MonitorListener.class);

    @Override
    public void notify(EventDataRepository eventDataRepo, EventData eventData) {
        log.info("ExperimentID: " + eventData.getExperimentID());
        log.info("Message: " + eventData.getMessage());
    }

    @Override
    public void setExperimentMonitor(Monitor monitor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void monitoringPreStart() {
        // TODO Auto-generated method stub

    }

    @Override
    public void monitoringPostStart() {
        // TODO Auto-generated method stub

    }

    @Override
    public void monitoringPreStop() {
        // TODO Auto-generated method stub

    }

    @Override
    public void monitoringPostStop() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFail(EventData failNotification) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onCompletion(EventData completionNotification) {
        // TODO Auto-generated method stub

    }

}
