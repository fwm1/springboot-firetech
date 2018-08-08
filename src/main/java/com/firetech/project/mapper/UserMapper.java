package com.firetech.project.mapper;

import com.firetech.project.model.SystemUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName SystemUserMapper
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/7/26 20:40
 * @Version 1.0
 **/
@Mapper
@Component
public interface UserMapper {

    @Select("select * from `user_` where user_name = #{userName}")
    @Results({
            /*这里要指明属性groupId与表字段的映射group_id*/
            @Result(id = true, property = "userId", column = "user_id"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "role",column = "role"),
            @Result(property = "password", column = "password"),
            @Result(property = "info",column = "info"),
            @Result(property = "routeList", column = "group_id",javaType = List.class,
                    many = @Many(select = "com.firetech.project.mapper.RouteMapper.findRouteByGroupId")
            )
    })
    SystemUser select(String userName);

    @Select("select * from user_ where role = 1")
    @Results({
            /*这里要指明属性groupId与表字段的映射group_id*/
            @Result(id = true, property = "userId", column = "user_id"),
            @Result(property = "groupId", column = "group_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "role",column = "role"),
            @Result(property = "password", column = "password"),
            @Result(property = "info",column = "info"),
            @Result(property = "routeList", column = "group_id",javaType = List.class,
                    many = @Many(select = "com.firetech.project.mapper.RouteMapper.findRouteByGroupId")
            )
    })
    List<SystemUser> selectAll();

    @Select("select * from user_ where group_id = #{group_id}")
    List<SystemUser> findUserByGroup(String group_id);

    @Insert("insert into user_(user_name,password,role,group_id,info) values(#{user_name},#{password},1,#{group_id},#{info})")
    void addUser(@Param("user_name") String user_name,@Param("password") String password,
                 @Param("group_id") int group_id,@Param("info") String info);

    @Delete("delete from user_ where user_name = #{user_name}")
    void deleteUser(@Param("user_name")String username);
}
