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
 * Apache Airavata configuration holder class
 */
public class AiravataConfig {
    private  int port;
    private  String serverUrl;
    private  String serverContextName;

    private  String gatewayName;
    private  String userName;
    private  String password;
    private  String jcr;
    private  String messageBox;
    private  String broker;
    private  String gfac;

    public AiravataConfig() throws PortalException {
        if(isAiravataConfigurationExists()) {
            setAiravataConfiguration();
        } else {
            setDefaultConfig();
        }
    }

    /**
     * Check whether portal configuration is exist in the configuration file location
     * @return
     */
    private boolean isAiravataConfigurationExists() {
        File file=new File("src/main/resources/portal_configuration.xml");
        return file.exists();
    }

    /**
     * Set defualt config
     * @throws PortalException
     */
    private void setDefaultConfig() throws PortalException {
        this.setPassword("admin");
        this.setUserName("admin");
        this.setGatewayName("default");
        this.setPort(8081);
        this.setServerContextName("airavata-registry");
        this.setServerUrl("localhost");
    }

    /**
     * If config exists create airavata configuration from xml
     * @throws PortalException
     */
    private void setAiravataConfiguration() throws PortalException {
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
        OMElement airavataConfig=documentElement.getFirstChildWithName(new QName("airavata-configuration"));
        OMElement server=airavataConfig.getFirstElement();
        this.setPassword(server.getFirstChildWithName(new QName("username")).getText().toString());
        this.setUserName(server.getFirstChildWithName(new QName("password")).getText().toString());
        this.setGatewayName(server.getFirstChildWithName(new QName("gateway-name")).getText().toString());
        this.setPort(Integer.parseInt(server.getFirstChildWithName(new QName("port")).getText().toString()));
        this.setServerContextName(server.getFirstChildWithName(new QName("server-context")).getText().toString());
        this.setServerUrl(server.getFirstChildWithName(new QName("server-url")).getText().toString());
        this.setBroker(server.getFirstChildWithName(new QName("broker")).getText().toString());
        this.setGfac(server.getFirstChildWithName(new QName("gfac")).getText().toString());
        this.setMessageBox(server.getFirstChildWithName(new QName("message-box")).getText().toString());
        this.setJcr(server.getFirstChildWithName(new QName("jcr")).getText().toString());
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getServerContextName() {
        return serverContextName;
    }

    public void setServerContextName(String serverContextName) {
        this.serverContextName = serverContextName;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
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

    public String getMessageBox() {
        return messageBox;
    }

    public void setMessageBox(String messageBox) {
        this.messageBox = messageBox;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getGfac() {
        return gfac;
    }

    public void setGfac(String gfac) {
        this.gfac = gfac;
    }

    public String getJcr() {
        return jcr;
    }

    public void setJcr(String jcr) {
        this.jcr = jcr;
    }
}
