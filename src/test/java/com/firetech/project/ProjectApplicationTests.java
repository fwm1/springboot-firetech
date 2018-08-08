package com.firetech.project;

import com.firetech.project.mapper.RouteMapper;
import com.firetech.project.mapper.UserMapper;
import com.firetech.project.model.Route;
import com.firetech.project.model.SystemUser;
import com.firetech.project.util.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {
	@Autowired
	UserMapper userMapper;
	@Autowired
	RouteMapper routeMapper;
	@Test
	public void contextLoads() {
		List<SystemUser> userList = userMapper.selectAll();
		for (SystemUser user:userList){
			System.out.println(user.getUserName() + "   "+user.getRouteList().size());
			if(user.getRouteList().size()>0){
				for(Route route: user.getRouteList()){
					System.out.println(route.getRouteName());
				}
			}
		}
	}

}
