package com.firetech.project.service;

import com.firetech.project.mapper.ScriptMapper;
import com.firetech.project.model.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ScriptService
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/8/8 19:10
 * @Version 1.0
 **/
@Component
@Service
public class ScriptService {
    @Autowired
    ScriptMapper scriptMapper;

    public List<Script> findAllScript(){
        return scriptMapper.findAllScript();
    }

    public void deleteScript(int scriptId){
        scriptMapper.deleteScript(scriptId);
    }

    public void addScript(String content){
        scriptMapper.addScript(content);
    }
}
