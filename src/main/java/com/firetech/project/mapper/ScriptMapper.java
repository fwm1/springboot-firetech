package com.firetech.project.mapper;

import com.firetech.project.model.Script;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @InterfaceName ScriptMapper
 * @ProjectName project
 * @Description TODO
 * @Author 万民
 * @Date 2018/8/8 18:05
 * @Version 1.0
 **/
@Mapper
@Component
public interface ScriptMapper {
    @Insert("insert into script_ values(null, #{content})")
    void addScript(@Param("content") String content);

    @Delete("delete from script_ where script_id = #{script_id}")
    void deleteScript(@Param("script_id")int scriptId);

    @Select("select * from script_")
    @Results({
            @Result(id = true, property = "scriptId", column = "script_id"),
            @Result(property = "content",column = "content"),
            @Result(property = "routeList", column = "script_id",javaType = List.class,
                    many = @Many(select = "com.firetech.project.mapper.RouteMapper.findRouteByScriptId")
            )
    })
    List<Script> findAllScript();
}
