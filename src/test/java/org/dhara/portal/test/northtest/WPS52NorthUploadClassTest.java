package org.dhara.portal.test.northtest;

import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 11/12/13
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class WPS52NorthUploadClassTest {
    WPS52Manager wps52Manager;
    @Before
    public void setUp() throws Exception {
        wps52Manager=WPS52Manager.getInstance();
    }

    @Test
    public void testRunWorkflow() throws Exception {
        //wps52Manager.uploadClass(null,null);
    }
}
