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

import org.dhara.portal.test.exception.PortalException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 52 North WPS manager class for access functions
 */
public class WPS52Manager {

    private WPS52NorthConfig wps52NorthConfig;
    private static WPS52Manager wps52Manager;

    private WPS52Manager() throws PortalException {
        wps52NorthConfig=new WPS52NorthConfig();
    }

    public static WPS52Manager getInstance() throws PortalException {
        if(wps52Manager==null)
        {
           return new WPS52Manager();
        } else {
            return wps52Manager;
        }
    }

    public String uploadClass(String generatedClass,String className) throws IOException {

        String defaultPackage = WPS52NorthConfig.defaultPackage;
        String classNameWithPackages = defaultPackage.substring(8,defaultPackage.length()-1)+"."+className;
        System.out.println("class name with pckg is"+classNameWithPackages);

        String encodedClass = URLEncoder.encode(generatedClass, WPSNorthServiceConstants.UTF8);
        String encodedClassName=URLEncoder.encode(classNameWithPackages, WPSNorthServiceConstants.UTF8);

        String inputAdjusted = "class=" + encodedClass+"&"+"classname="+encodedClassName;

        URL obj = new URL(wps52NorthConfig.getServerUrl());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setDoOutput(true);
        con.setDoInput(true);
        // optional default is GET
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("charset", "utf-8");
        con.setRequestProperty("Content-Length", "" + Integer.toString(inputAdjusted.getBytes().length));
        con.setUseCaches(false);

        //add request header
        con.setRequestProperty("User-Agent", WPSNorthServiceConstants.USER_AGENT);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(inputAdjusted);
        wr.flush();
        wr.close();
        con.disconnect();

        int responseCode = con.getResponseCode();
        /*System.out.println("\nSending 'POST' request to URL : " + WPSNorthServiceConstants.WPS_52NORTH_URL);
        System.out.println("Post parameters : " + encoded);
        System.out.println("Response Code : " + responseCode);
        */
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
        /*//print result
       /* System.out.println(response.toString());*/
            //return response.toString();
    }

    public WPS52NorthConfig getWps52NorthConfig() {
        return wps52NorthConfig;
    }

    public void setWps52NorthConfig(WPS52NorthConfig wps52NorthConfig) {
        this.wps52NorthConfig = wps52NorthConfig;
    }
}
