package com.firetech.project.controller;

import com.firetech.project.model.Route;
import com.firetech.project.model.SystemUser;
import com.firetech.project.service.RouteService;
import com.firetech.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName TestController
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/7/26 16:55
 * @Version 1.0
 **/

@Controller
public class MainController {
    @Autowired
    UserService userService;
    @Autowired
    RouteService routeService;

    @RequestMapping("/")
    public String index(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/identify")
    public ModelAndView identify(@AuthenticationPrincipal SystemUser user){
        ModelAndView mv = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().toString().equals("[ADMIN]")){
            mv.setViewName("redirect:/admin");
        }else{
            mv.setViewName("redirect:/index");
        }
        return mv;
    }

    @RequestMapping("/admin")
    public ModelAndView admin(@RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        List<SystemUser> userList = userService.findAllUser(pageNum,pageSize);
        PageInfo<SystemUser> page_user = new PageInfo<>(userList);
        List<Route> routeList = routeService.findAllRoute(pageNum, pageSize);
        PageInfo<Route> page_route = new PageInfo<>(routeList);
        ModelAndView mv = new ModelAndView();
        mv.addObject("page_user",page_user);
        mv.addObject("page_route",page_route);
        mv.setViewName("admin");
        return mv;
    }
    @RequestMapping("/index")
    public ModelAndView index(@AuthenticationPrincipal SystemUser user,
                              @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Route> routeList = routeService.findRouteByUserName(user.getUsername());
        ModelAndView mv = new ModelAndView();
        PageInfo<Route> page = new PageInfo<>(routeList);
        mv.addObject("page",page);
        mv.addObject("info",user.getInfo());
        mv.setViewName("index");
        return mv;
    }
    @RequestMapping("/failure")
    public String failure(){
        return "failure";
    }
}
