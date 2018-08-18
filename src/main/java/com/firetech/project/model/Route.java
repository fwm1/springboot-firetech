package com.firetech.project.model;

import java.util.List;

/**
 * @ClassName Route
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/7/30 11:10
 * @Version 1.0
 **/
public class Route {
    private String routeName;
    private String routeInstruct;
    private String outIp;
    private String info;
    private int groupId;
    private int scriptId;
    private List<SystemUser> userList;

    public Route(){}
    public Route(String routeName, String routeInstruct, String outIp, String info,int groupId,int scriptId) {
        this.routeName = routeName;
        this.routeInstruct = routeInstruct;
        this.outIp = outIp;
        this.info = info;
        this.groupId = groupId;
        this.scriptId = scriptId;
    }

    public int getScriptId() {
        return scriptId;
    }

    public void setScriptId(int scriptId) {
        this.scriptId = scriptId;
    }

    public String getOutIp() {
        return outIp;
    }

    public void setOutIp(String outIp) {
        this.outIp = outIp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteInstruct() {
        return routeInstruct;
    }

    public void setRouteInstruct(String routeInstruct) {
        this.routeInstruct = routeInstruct;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<SystemUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SystemUser> userList) {
        this.userList = userList;
    }
}
