package com.stx.dao;

import com.stx.entity.Admin;
import com.stx.entity.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Insert(value = "INSERT INTO course(coursename,coursescore,introduce,professid,flag) VALUES (#{coursename},#{coursescore},#{introduce},#{profess.professId},1)")
    @Options(useGeneratedKeys = true,keyProperty = "courseid")
    void addCourse(Course course);

    @Insert("INSERT INTO upclass(professid,courseid,flag) VALUES(#{profess.professId},#{courseid},1)")
    void addDesign(Course course);

    @Update("UPDATE course SET flag = 0 WHERE courseid = #{courseid}")
    void delById(Integer courseid);

    @Select("SELECT * FROM course WHERE courseid = #{courseid} AND flag=1")
    Course findCourseById(Integer courseid);

    @Select("SELECT * FROM course WHERE flag=1 LIMIT #{start},#{rows}")
    List<Course> findAllCourse(Integer start,Integer rows);

    @Update("UPDATE course SET coursename=#{coursename},coursescore=#{coursescore} WHERE courseid = #{courseid}")
    void updateCourse(Course course);

    @Select("SELECT * FROM course WHERE flag=1 AND professid=#{professid}")
    List<Course> findByProfessId(Integer professid);

    @Select("SELECT COUNT(courseid) FROM course WHERE flag = 1")
    Integer findTotalCount();

    @Update("UPDATE upclass SET flag = 0 WHERE courseid = #{courseid} AND professid = #{professid}")
    void delDesign(Integer courseid,Integer professid);

    @Select("SELECT * FROM course WHERE flag = 1 AND coursename LIKE #{search} LIMIT #{start},#{rows}")
    List<Course> searchCourse(String search,int start,int rows);

    @Select("SELECT COUNT(courseid) FROM course WHERE flag = 1 AND coursename LIKE #{search}")
    Integer searchCount(String search);



}
