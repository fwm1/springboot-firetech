package com.firetech.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.firetech.project.model.Route;
import com.firetech.project.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
    public void changeRoute(@RequestParam("info")String info, @RequestParam("route")String routeName,@RequestParam("username")String userName){
        routeService.changeRoute(info,routeName,userName);
    }

    @RequestMapping(value = "/addRoute",method = RequestMethod.POST)
    @ResponseBody
    public String addRoute(@RequestParam("route_name")String routeName,@RequestParam("route_instruct")String routeInstruct,@RequestParam("group")int groupId,
                         @RequestParam("route_out_ip")String outIp,@RequestParam("route_info")String routeInfo){
        routeService.addRoute(routeName, routeInstruct, groupId,outIp,routeInfo);
        Route route = new Route(routeName,routeInstruct,outIp,routeInfo,groupId,0);
        return JSONObject.toJSONString(route);
    }

    @RequestMapping(value = "/deleteRoute", method = RequestMethod.POST)
    @ResponseBody
    public void deleteRoute(@RequestParam("route_name")String routeName){
        routeService.deleteRoute(routeName);
    }

    @RequestMapping(value = "/updateRoute")
    @ResponseBody
    public void updateRoute(@RequestParam("route_instruct") String instruct, @RequestParam("route_out_ip") String outIp,
                            @RequestParam("route_info") String info,@RequestParam("script_id") int scriptId,
                            @RequestParam("group_id") int groupId,@RequestParam("route_name")String routeName){
        routeService.updateRoute(instruct,outIp,info,scriptId,groupId,routeName);
    }

}
