package com.stx.dao;

import com.stx.entity.Grade;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface GradeMapper {

    @Insert("INSERT INTO grade(courseid,courdate,studentid,grade,flag) VALUES(#{course.courseid},#{courdate},#{student.studentid},#{grade},1)")
    void addGrade(Grade grade);

    @Results(id = "gradeMap", value = {
            @Result(property = "id",column = "id"),
            @Result(property = "courdate",column = "courdate"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "grade",column = "grade"),
            @Result(property = "course",column = "courseid",one=@One(select = "com.stx.dao.CourseMapper.findCourseById",fetchType = FetchType.EAGER)),
            @Result(property = "student",column = "studentid",one=@One(select = "com.stx.dao.StudentMapper.findStudentById",fetchType = FetchType.EAGER))
    })
    @Select("SELECT * FROM grade WHERE flag =1 ")
    List<Grade> findAllGrade();

    @ResultMap("gradeMap")
    @Select("SELECT * FROM grade WHERE flag =1 AND id = #{id}")
    Grade findGradeById(Integer id);

    @ResultMap("gradeMap")
    @Select("SELECT * FROM grade WHERE flag =1 AND studentid = #{studentid}")
    List<Grade> findGradeBystudentid(Integer studentid);

    @ResultMap("gradeMap")
    @Select("SELECT * FROM grade WHERE flag =1 AND courseid = #{course.courseid}")
    List<Grade> findGradeBycourseid(Integer courseid);

    @Update("UPDATE grade SET grade=#{grade} WHERE id = #{id}")
    void updateGrade(Grade grade);

    @Update("UPDATE grade SET flag=0 WHERE id = #{id}")
    void delGrade(Grade grade);








}
