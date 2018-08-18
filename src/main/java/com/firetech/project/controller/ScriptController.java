package com.firetech.project.controller;

import com.alibaba.fastjson.JSON;
import com.firetech.project.model.Script;
import com.firetech.project.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String addScript(@RequestParam("content") String content){
        scriptService.addScript(content);
        List<Script> list = scriptService.findAllScript();
        Script script = list.get(list.size()-1);
        return JSON.toJSONString(script);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public void deleteScript(@RequestParam("script_id") int id){
        scriptService.deleteScript(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public void updateScript(@RequestParam("script_id") int id, @RequestParam("content")String content){
        scriptService.updateScript(content, id);
    }
}
