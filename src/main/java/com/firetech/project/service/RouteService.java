package com.firetech.project.service;

import com.firetech.project.mapper.RouteMapper;
import com.firetech.project.model.Route;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @ClassName RouteService
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/7/31 9:35
 * @Version 1.0
 **/
@Component
@Service
public class RouteService {
    @Autowired
    RouteMapper routeMapper;

    public List<Route> findRouteByUserName(String userName){
        return routeMapper.findRoutesByUserName(userName);
    }

    public List<Route> findAllRoute(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return routeMapper.findAllRoute();
    }

    public void addRoute(String routeName,String routeInstruct,int groupId,String outIp, String info){
        routeMapper.addRoute(routeName, routeInstruct, groupId,outIp,info);
    }

    public void deleteRoute(String routeName){
        routeMapper.deleteRoute(routeName);
    }

    public void changeRoute(String info, String routeName,String userName){
        Route route = routeMapper.findRouteByRouteName(routeName);
        routeMapper.updateUserCur(routeName,userName);
        String instruct = route.getRouteInstruct();
        String remoteAddress = instruct.split(":")[0];
        String remotePort = instruct.split(":")[1];
        String outIp = route.getOutIp();
        String script = routeMapper.getScript(routeName);
        String[] cmd = {script,info,remoteAddress,remotePort,outIp};
        /*try{
            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }

    public void updateRoute(String route_instruct,String out_ip,String info,int script_id,int group_id,String route_name){
        routeMapper.updateRoute(route_instruct, out_ip, info, script_id, group_id, route_name);
    }
}
