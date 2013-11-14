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
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 11/12/13
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class WPS52NorthUploadClassTest {
    WPS52Manager wps52Manager;
    private HttpURLConnection testJSPCon;
    private String[] testResult;

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




    public void upload() throws IOException {


              WPS52NorthConfig wps52NorthConfig=wps52Manager.getWps52NorthConfig();

              wps52NorthConfig.setServerUrl(wps52NorthConfig.getServerUrl());

              wps52Manager.uploadClass(TestData.classContent, TestData.className);

      }

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


    @Test
    public void testClassUpload(){
                   assertEquals("uploadingOk",testResult[0]);
    }


     @Test
    public void testCompileClass(){

         assertEquals("compileOk",testResult[1]);
     }


    @Test
    public void testWPSHost(){

        assertEquals("localhost",testResult[2]);
    }

    @Test
    public void testWPSPort(){

        assertEquals("8090",testResult[3]);
    }
    @Test
    public void testWebAppPath(){

        assertEquals("52n-wps-webapp-3.3.0-SNAPSHOT",testResult[4]);
    }
    @Test
    public void testGeoserverHost(){

        assertEquals("localhost",testResult[5]);
    }
    @Test
    public void testGeoserverPassword(){

        assertEquals("geoserver",testResult[6]);
    }
    @Test
    public void testGeoserverPort(){

        assertEquals("8094",testResult[7]);
    }
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
