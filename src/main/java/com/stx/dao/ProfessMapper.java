package com.stx.dao;

import com.stx.entity.Profess;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ProfessMapper {

    @Results(id = "professMap",value = {
            @Result(property = "professId",column = "professid"),
            @Result(property = "professName",column = "professname"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "dept",column = "deptno",one = @One(
                    select = "com.stx.dao.DeptMapper.findDeptByIdND",
                    fetchType = FetchType.EAGER
            )),
            @Result(property = "classes",column = "professid",many = @Many(
                    select = "com.stx.dao.ClassesMapper.findByProfessId",
                    fetchType = FetchType.LAZY
            ))
    })
    @Select("SELECT * FROM profess WHERE flag = 1")
    List<Profess> findAll();

    @ResultMap("professMap")
    @Select("SELECT * FROM profess WHERE flag = 1 AND professid = #{professid}")
    List<Profess> findById(Integer professid);

    @Results(id = "professMaps" +
            "" +
            "" +
            "",value = {
            @Result(property = "professId",column = "professid"),
            @Result(property = "professName",column = "professname"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "dept",column = "deptno",one = @One(
                    select = "com.stx.dao.DeptMapper.findDeptByIdND",
                    fetchType = FetchType.EAGER
            ))
    })
    @Select("SELECT * FROM profess WHERE flag = 1 AND professid = #{professid}")
    List<Profess> findByIdND(Integer professid);

    @ResultMap("professMap")
    @Select("SELECT * FROM profess WHERE flag = 1 AND deptno = #{deptno}")
    List<Profess> findByDeptno(Integer deptno);

    @ResultMap("professMap")
    @Select("SELECT * FROM profess WHERE flag = 1 AND professid IN (SELECT u.professid FROM course c,upclass u WHERE c.courseid = u.courseid AND c.courseid = #{courseid} AND u.flag=1);")
    List<Profess> findByCourseNo(Integer courseId);





}
