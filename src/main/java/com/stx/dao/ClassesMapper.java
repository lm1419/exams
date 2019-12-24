package com.stx.dao;

import com.stx.entity.Classes;
import com.stx.entity.Course;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ClassesMapper {

    @Results(id = "classesMap",value = {
            @Result(property = "classesId",column = "classid"),
            @Result(property = "classesName",column = "classname"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "profess",column = "professid",one = @One(
                    select = "com.stx.dao.ProfessMapper.findByIdND",
                    fetchType = FetchType.EAGER
            )),
            @Result(property = "students",column = "classesid",many = @Many(
                    select = "com.stx.StudentMapper.findStudentByClassesId",
                    fetchType = FetchType.LAZY
            ))
    })
    @Select("SELECT * FROM classes WHERE flag = 1")
    List<Classes> findAllClasses();

    @ResultMap("classesMap")
    @Select("SELECT * FROM classes WHERE flag = 1 AND classid = #{classid}")
    List<Classes> findById();

    @ResultMap("classesMap")
    @Select("SELECT * FROM classes WHERE flag = 1 AND professid = #{professid}")
    List<Classes> findByProfessId(Integer professid);


    @Results(id = "classesMap2",value = {
            @Result(property = "classesId",column = "classid"),
            @Result(property = "classesName",column = "classname"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "profess",column = "professid",one = @One(
                    select = "com.stx.dao.ProfessMapper.findByIdND",
                    fetchType = FetchType.EAGER
            )),
    })

    @Select("SELECT * FROM classes WHERE flag = 1 AND classid = #{classid}")
    List<Classes> findByIdND();


}
