package com.firetech.project.controller;

import com.firetech.project.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ScriptController
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/8/8 19:15
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/script")
public class ScriptController {
    @Autowired
    ScriptService scriptService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public void addScript(@RequestParam("content") String content){
        scriptService.addScript(content);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public void deleteScript(@RequestParam("script_id") int id){
        scriptService.deleteScript(id);
    }
}
