package com.firetech.project.service;

import com.firetech.project.mapper.UserMapper;
import com.firetech.project.model.SystemUser;
import com.firetech.project.util.MD5Util;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserService
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/7/26 20:37
 * @Version 1.0
 **/

@Component
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SystemUser user = userMapper.select(s);
        if(user == null)
            throw new UsernameNotFoundException("用户名不存在");
        return user;
    }

    public List<SystemUser> findAllUser(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return  userMapper.selectAll();
    }

    public void addUser(String username,String rowPassword,int group_id,String info){
        String password = MD5Util.encode(rowPassword);
        userMapper.addUser(username, password, group_id,info);
    }

    public void deleteUser(String username){
        userMapper.deleteUser(username);
    }

    public void updateAdmin(String newName,String rowPassword,String oldName){
        String password = MD5Util.encode(rowPassword);
        userMapper.updateAdmin(newName, password, oldName);
    }
    public String findCurrentRoute(String userName){
        return userMapper.findCurrentRoute(userName);
    }

    public void updateUser(String info, String username){
        userMapper.updateUser(info, username);
    }
}
