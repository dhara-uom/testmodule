package org.dhara.portal.test.helper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: harsha
 * Date: 8/10/13
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class WorkflowHelper {
    private String name;
    private String description;
    private List<InputHelper> inputs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InputHelper> getInputs() {
        return inputs;
    }

    public void setInputs(List<InputHelper> inputs) {
        this.inputs = inputs;
    }
}
