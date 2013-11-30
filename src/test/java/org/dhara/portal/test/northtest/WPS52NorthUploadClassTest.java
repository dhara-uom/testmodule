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

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Test class for 52 North functions
 */
public class WPS52NorthUploadClassTest {
    WPS52Manager wps52Manager;
    private HttpURLConnection testJSPCon;
    private String[] testResult;

    //initialize
    @Before
    public void setUp() throws Exception {
        wps52Manager=WPS52Manager.getInstance();
        testJspConnect();
        setParameters();
        upload();
        sendRequestTestJSP();

    }

    private void setParameters() throws ProtocolException {
        WPS52NorthConfig wps52NorthConfig=wps52Manager.getWps52NorthConfig();
        String testURL=  wps52NorthConfig.getTestURL();
        // wps52NorthConfig.setServerUrl(testURL);

    }

    //test jsp connection
    private void testJspConnect(){
        URL obj = null;
        try {
            obj = new URL(wps52Manager.getWps52NorthConfig().getTestURL());
            testJSPCon = (HttpURLConnection) obj.openConnection();
            assertNotNull("Connection ok",testJSPCon);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        finally {
            testJSPCon.disconnect();
        }
    }

    //Upload class
    public void upload() throws IOException {
        WPS52NorthConfig wps52NorthConfig=wps52Manager.getWps52NorthConfig();
        wps52NorthConfig.setServerUrl(wps52NorthConfig.getServerUrl());
        wps52Manager.uploadClass(TestData.classContent, TestData.className);

    }

    //Test for check the connection
    @Test
    public void connect() {
        URL obj = null;
        try {
            obj = new URL(wps52Manager.getWps52NorthConfig().getServerUrl());
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            assertNotNull("Connection ok",con);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

    private void sendRequestTestJSP() throws IOException {
        WPS52NorthConfig wps52NorthConfig=wps52Manager.getWps52NorthConfig();
        wps52NorthConfig.setServerUrl(wps52NorthConfig.getTestURL());
        String out= wps52Manager.uploadClass(TestData.classContent, TestData.className);
        testResult= out.split("_");
    }

    //Test method for check whether class uploaded or not
    @Test
    public void testClassUpload(){
        assertEquals("uploadingOk",testResult[0]);
    }

    //Test method for check whether dummy class is compiled correctly
    @Test
    public void testCompileClass(){
        assertEquals("compileOk",testResult[1]);
    }

    // Test for 52 North WPS host
    @Test
    public void testWPSHost(){
        assertEquals("localhost",testResult[2]);
    }

    //Test for 52 North WPS port
    @Test
    public void testWPSPort(){
        assertEquals("8090",testResult[3]);
    }

    //Test for web appication path
    @Test
    public void testWebAppPath(){
        assertEquals("52n-wps-webapp-3.3.0-SNAPSHOT",testResult[4]);
    }

    //Test for geoserver host configuration
    @Test
    public void testGeoserverHost(){
        assertEquals("localhost",testResult[5]);
    }

    //Test for geoserver password
    @Test
    public void testGeoserverPassword(){

        assertEquals("geoserver",testResult[6]);
    }

    //Test for geoserver port
    @Test
    public void testGeoserverPort(){
        assertEquals("8094",testResult[7]);
    }

    //Test for geoserver username
    @Test
    public void testGeoserverUserName(){
        assertEquals("admin",testResult[8]);
    }
/*
    @Test
    public void testRunWorkflow() throws Exception {
       // wps52Manager.uploadClass(null,null);
    }*/
}
