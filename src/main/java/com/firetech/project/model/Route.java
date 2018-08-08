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
    private int groupId;
    private List<SystemUser> userList;

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
