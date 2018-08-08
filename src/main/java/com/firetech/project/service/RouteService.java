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

    public void addRoute(String routeName,String routeInstruct,int groupId){
        routeMapper.addRoute(routeName, routeInstruct, groupId);
    }

    public void deleteRoute(String routeName){
        routeMapper.deleteRoute(routeName);
    }

    public void changeRoute(String info, String routeName){
        String clientAddress = info.split(":")[0];
        String clientPort = info.split(":")[1];
        String instruct = routeMapper.getInstruct(routeName);
        String remoteAddress = instruct.split(":")[0];
        String remotePort = instruct.split(":")[1];
        /*try{
            Process process = Runtime.getRuntime().exec("echo password | sudo");
            process.waitFor();
            InputStream in = process.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));
            String result = read.readLine();
            System.out.println("command result:     "+result);

        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
