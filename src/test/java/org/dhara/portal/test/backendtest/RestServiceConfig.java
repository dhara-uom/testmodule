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
 *Backend REST service configuration holder
 */
public class RestServiceConfig {

    private  String serverUrl;
    private  String userName;
    private  String password;

    public RestServiceConfig() throws PortalException {
        if(isBackendConfigurationExists()) {
            setBackendConfiguration();
        } else {
            setDefaultConfig();
        }
    }

    /**
     * Check whether configuration is existing in the provided location
     * @return
     */
    private boolean isBackendConfigurationExists() {
        File file=new File("src/main/resources/portal_configuration.xml");
        return file.exists();
    }

    /**
     * Set default configuration
     * @throws PortalException
     */
    private void setDefaultConfig() throws PortalException {
        this.setPassword("admin");
        this.setUserName("admin");
        this.setServerUrl("http://localhost:8090/portal");
    }

    /**
     * Set portal configuration
     * @throws PortalException
     */
    private void setBackendConfiguration() throws PortalException {
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
        OMElement backendConfiguration=documentElement.getFirstChildWithName(new QName("backend-configuration"));
        OMElement server=backendConfiguration.getFirstElement();
        this.setPassword(server.getFirstChildWithName(new QName("username")).getText().toString());
        this.setUserName(server.getFirstChildWithName(new QName("password")).getText().toString());
        this.setServerUrl(server.getFirstChildWithName(new QName("server-url")).getText().toString());
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

}
