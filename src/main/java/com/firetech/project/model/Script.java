package com.firetech.project.model;

import java.util.List;

/**
 * @ClassName Script
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/8/8 18:55
 * @Version 1.0
 **/
public class Script {
    private int scriptId;
    private String content;
    private List<Route> routeList;

    public Script(){}

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }

    public int getScriptId() {
        return scriptId;
    }

    public void setScriptId(int scriptId) {
        this.scriptId = scriptId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
