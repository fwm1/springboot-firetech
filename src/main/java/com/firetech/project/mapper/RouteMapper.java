package com.firetech.project.mapper;

import com.firetech.project.model.Route;
import javafx.scene.effect.SepiaTone;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @InterfaceName RouteMapper
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/7/30 11:22
 * @Version 1.0
 **/

@Mapper
@Component
public interface RouteMapper {

    @Select("select * from route_ where group_id in (select group_id from user_ where user_name = #{user_name}) order by group_id,route_name")
    @Results({
            @Result(property = "routeName", column = "route_name"),
            @Result(property = "userList", column = "group_id",
                    many = @Many(select = "com.firetech.project.mapper.UserMapper.findUserByGroup"))
    })
    List<Route> findRoutesByUserName(String userName);

    @Select("select * from route_ where group_id = #{group_id}")
    /*
    * 属性与字段映射
    * */
    @Results({
            @Result(property = "routeName",column = "route_name"),
            @Result(property = "routeInstruct",column = "route_instruct"),
            @Result(property = "groupId",column = "group_id")
    })
    Route findRouteByGroupId(int group_id);

    @Insert("insert into route_(route_name,route_instruct,group_id) values(#{route_name},#{route_instruct},#{group_id})")
    void addRoute(@Param("route_name") String routeName,@Param("route_instruct") String routeInstruct,@Param("group_id") int groupId);

    @Delete("delete from route_ where route_name = #{route_name}")
    void deleteRoute(@Param("route_name")String routeName);

    @Select("select * from route_ order by group_id,route_name")
    @Results({
            @Result(property = "routeName",column = "route_name"),
            @Result(property = "routeInstruct",column = "route_instruct"),
            @Result(property = "groupId",column = "group_id")
    })
    List<Route> findAllRoute();

    @Select("select route_instruct from route_ where route_name = #{route_name}")
    String getInstruct(@Param("route_name")String routeName);
}
