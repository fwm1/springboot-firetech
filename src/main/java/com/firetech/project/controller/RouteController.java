package com.firetech.project.controller;

import com.firetech.project.mapper.RouteMapper;
import com.firetech.project.model.Route;
import com.firetech.project.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName RouteController
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/7/31 9:38
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/route")
public class RouteController {
    @Autowired
    RouteService routeService;

    @RequestMapping(value = "/change",method = RequestMethod.POST)
    @ResponseBody
    public void changeRoute(@RequestParam("info")String info, @RequestParam("route")String routeName){
        routeService.changeRoute(info,routeName);
    }

    @RequestMapping(value = "/addRoute",method = RequestMethod.POST)
    @ResponseBody
    public void addRoute(@RequestParam("route_name")String routeName,@RequestParam("route_instruct")String routeInstruct,@RequestParam("group")int groupId){
        routeService.addRoute(routeName, routeInstruct, groupId);
    }

    @RequestMapping(value = "/deleteRoute", method = RequestMethod.POST)
    @ResponseBody
    public void deleteRoute(@RequestParam("route_name")String routeName){
        routeService.deleteRoute(routeName);
    }
}
