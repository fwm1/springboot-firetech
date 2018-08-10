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
            @Result(property = "outIp", column = "out_ip"),
            @Result(property = "userList", column = "group_id",
                    many = @Many(select = "com.firetech.project.mapper.UserMapper.findUserByGroup"))
    })
    List<Route> findRoutesByUserName(String userName);

    @Select("select * from route_ where route_name = #{route_name}")
    @Results({
            @Result(property = "routeName", column = "route_name"),
            @Result(property = "routeInstruct",column = "route_instruct"),
            @Result(property = "outIp", column = "out_ip"),
            /*@Result(property = "userList", column = "group_id",
                    many = @Many(select = "com.firetech.project.mapper.UserMapper.findUserByGroup"))*/
    })
    Route findRouteByRouteName(String routeName);

    @Select("select * from route_ where group_id = #{group_id}")
    /*
    * 属性与字段映射
    * */
    @Results({
            @Result(property = "routeName",column = "route_name"),
            @Result(property = "routeInstruct",column = "route_instruct"),
            @Result(property = "outIp", column = "out_ip"),
            @Result(property = "scriptId", column = "script_id"),
            @Result(property = "groupId",column = "group_id")
    })
    Route findRouteByGroupId(int group_id);

    @Select("select * from route_ where script_id = #{script_id}")
    @Results({
            @Result(property = "routeName",column = "route_name"),
            @Result(property = "routeInstruct",column = "route_instruct"),
            @Result(property = "outIp", column = "out_ip"),
            @Result(property = "scriptId", column = "script_id"),
            @Result(property = "groupId",column = "group_id")
    })
    Route findRouteByScriptId(int script_id);

    @Insert("insert into route_(route_name,route_instruct,group_id,out_ip,info) values(#{route_name},#{route_instruct},#{group_id},#{out_ip},#{info})")
    void addRoute(@Param("route_name") String routeName,@Param("route_instruct") String routeInstruct,@Param("group_id") int groupId,
                  @Param("out_ip")String outIp,@Param("info")String info);

    @Delete("delete from route_ where route_name = #{route_name}")
    void deleteRoute(@Param("route_name")String routeName);

    @Select("select * from route_ order by group_id,route_name")
    @Results({
            @Result(property = "routeName",column = "route_name"),
            @Result(property = "routeInstruct",column = "route_instruct"),
            @Result(property = "outIp", column = "out_ip"),
            @Result(property = "scriptId", column = "script_id"),
            @Result(property = "groupId",column = "group_id")
    })
    List<Route> findAllRoute();

    /*@Select("select route_instruct from route_ where route_name = #{route_name}")
    String getInstruct(@Param("route_name")String routeName);*/

    @Update("update route_ set route_instruct=#{route_instruct},out_ip=#{out_ip}," +
            "info=#{info},script_id=#{script_id},group_id=#{group_id} where route_name=#{route_name}")
    void updateRoute(@Param("route_instruct")String route_instruct,@Param("out_ip")String out_ip,
                     @Param("info")String info,@Param("script_id")int script_id,
                     @Param("group_id")int group_id,@Param("route_name")String route_name);

    @Update("update user_ set cur_route=#{route_name} where user_name=#{user_name}")
    void updateUserCur(@Param("route_name")String routeName,@Param("user_name")String userName);

    @Select("select content from script_ where script_id = (select script_id  from route_ where route_name = #{route_name})")
    String getScript(@Param("route_name")String routeName);
}
