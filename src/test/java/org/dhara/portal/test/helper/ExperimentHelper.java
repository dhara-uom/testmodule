package org.dhara.portal.test.helper;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nipuni
 * Date: 11/13/13
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExperimentHelper {

    private String name;
    private String author;
    private Date updatedTime;
    private String state;
    private List<Nodehelper> nodehelperList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Nodehelper> getNodehelperList() {
        return nodehelperList;
    }

    public void setNodehelperList(List<Nodehelper> nodehelperList) {
        this.nodehelperList = nodehelperList;
    }
}
