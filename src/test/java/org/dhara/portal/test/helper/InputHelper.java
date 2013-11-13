package org.dhara.portal.test.helper;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 11/13/13
 * Time: 6:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputHelper {

    private String name;
    private String rawName;
    private String type;
    private String[] values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }
}
