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
package org.dhara.portal.test.northtest;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.dhara.portal.test.exception.PortalException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 52 North WPS configuration holder class
 */
public class WPS52NorthConfig {
    private  String serverUrl;
    private  String userName;
    private  String password;
    public static final String defaultPackage ="package org.dhara.wps;";
    private String testURL;


    public WPS52NorthConfig() throws PortalException {
        if(isWPS52NorthConfigurationExists()) {
            set52NorthConfiguration();
        } else {
            setDefaultConfig();
        }
    }

    /**
     * Check whether configuration is exists in given location
     * @return
     */
    private boolean isWPS52NorthConfigurationExists() {
        File file=new File("src/main/resources/portal_configuration.xml");
        return file.exists();
    }

    private void setDefaultConfig() throws PortalException {
        this.setPassword("admin");
        this.setUserName("admin");
        this.setServerUrl("http://localhost:8090/52n-wps-webapp-3.3.0-SNAPSHOT/webAdmin/DynamicDeployProcesstest.jsp");
        this.setTestURL("http://localhost:8090/52n-wps-webapp-3.3.0-SNAPSHOT/webAdmin/TestDeploy.jsp");
    }

    private void set52NorthConfiguration() throws PortalException {
        File file= new File("src/main/resources/portal_configuration.xml");
        FileInputStream fis;
        XMLInputFactory xif;
        XMLStreamReader reader;
        StAXOMBuilder builder;
        try {
            fis= new FileInputStream(file);
            xif= XMLInputFactory.newInstance();
            reader= xif.createXMLStreamReader(fis);
            builder= new StAXOMBuilder(reader);
        } catch (FileNotFoundException e) {
            throw new PortalException(e.getMessage(),e);
        } catch (XMLStreamException e) {
            throw new PortalException(e.getMessage(),e);
        }

        OMElement documentElement= builder.getDocumentElement();
        OMElement northConfiguration=documentElement.getFirstChildWithName(new QName("wps-52north-configuration"));
        OMElement nserver=northConfiguration.getFirstElement();
        this.setPassword(nserver.getFirstChildWithName(new QName("username")).getText().toString());
        this.setUserName(nserver.getFirstChildWithName(new QName("password")).getText().toString());
        this.setServerUrl(nserver.getFirstChildWithName(new QName("server-url")).getText().toString());
        this.setServerUrl(nserver.getFirstChildWithName(new QName("test-url")).getText().toString());
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTestURL() {
        return testURL;
    }

    public void setTestURL(String testURL) {
        this.testURL = testURL;
    }
}
