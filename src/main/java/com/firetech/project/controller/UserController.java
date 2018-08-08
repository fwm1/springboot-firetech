package com.firetech.project.controller;

import com.firetech.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName UserController
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/7/27 14:43
 * @Version 1.0
 **/
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/user/addUser")
    @ResponseBody
    public void addUser(@RequestParam("username")String userName, @RequestParam("password")String password,
                        @RequestParam("group")int group,@RequestParam("info")String info){
        userService.addUser(userName, password, group,info);
    }
    @RequestMapping("/user/delete")
    @ResponseBody
    public void deleteUser(@RequestParam("username")String username){
        userService.deleteUser(username);
    }
}
