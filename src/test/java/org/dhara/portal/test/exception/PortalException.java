package org.dhara.portal.test.exception;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 7/20/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class PortalException extends Exception {
    public PortalException(String ex, Exception e) {
        super(ex,e);
    }

    public PortalException(String ex) {
        super(ex);
    }
}
