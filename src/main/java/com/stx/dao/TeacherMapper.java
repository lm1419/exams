package com.stx.dao;

import com.stx.entity.Student;
import com.stx.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface TeacherMapper {

    @Insert("INSERT INTO teacher(name,sex,birthday,deptno,ptitle,studyhistory,politapperance,password,flag) VALUES(#{name},#{sex}, #{birthday},#{dept.deptno},#{ptitle},#{studyhistory},#{politapperance},#{password},1)")
    void addTeacher(Teacher teacher);

    @Update("UPDATE teacher SET flag = 0 WHERE teacherid = #{teacherid}")
    void delTeacher(Integer teacherid);

    @Update("UPDATE teacher SET name=#{name},sex=#{sex},birthday=#{birthday},deptno=#{dept.deptno},ptitle=#{ptitle},politapperance=#{politapperance},studyhistory=#{studyhistory},password=#{password},flag = 1 WHERE teacherid=#{teacherid}")
    void updateTeacher(Teacher teacher);

    @Results(id = "teacherMap", value = {
            @Result(property = "teacherid",column = "teacherid"),
            @Result(property = "name",column = "name"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "ptitle",column = "ptitle"),
            @Result(property = "studyhistory",column = "studyhistory"),
            @Result(property = "politapperance",column = "politapperance"),
            @Result(property = "password",column = "password"),
            @Result(property = "flag",column = "flag"),
            @Result(property = "count",column = "COUNT(teacherid)"),
            @Result(property = "dept", column = "deptno", one = @One(select = "com.stx.dao.DeptMapper.findDeptById", fetchType = FetchType.EAGER))
    }
    )
    @Select("SELECT * FROM teacher WHERE flag = 1 LIMIT #{start},#{rows}")
    List<Teacher> findAll(int start,int rows);


    @ResultMap("teacherMap")
    @Select("SELECT * FROM teacher WHERE flag = 1 AND teacherid = #{teacherid}")
    Teacher findTeacherById(Integer teacherid);

    @ResultMap("teacherMap")
    @Select("SELECT * FROM teacher WHERE flag = 1 AND teacherid = #{teacherid} AND password = #{password}")
    Teacher findTeacherByIdAndPassword(Teacher teacher);

    @Select("SELECT COUNT(teacherid) FROM teacher WHERE flag = 1")
    Integer findTotalCount();

    @ResultMap("teacherMap")
    @Select("SELECT * FROM teacher t,dept d WHERE t.deptno=d.deptno AND(t.name LIKE #{search} OR d.deptname LIKE #{search} OR t.ptitle LIKE #{search} OR t.studyhistory LIKE #{search}) AND t.teacherid IS NOT NULL AND t.flag=1 LIMIT #{start},#{rows}")
    List<Teacher> findTeacherBySearch(String search,int start,int rows);


    @Select("SELECT COUNT(*) FROM teacher t,dept d WHERE t.deptno=d.deptno AND(t.name LIKE #{search} OR d.deptname LIKE #{search} OR t.ptitle LIKE #{search} OR t.studyhistory LIKE #{search}) AND t.teacherid IS NOT NULL AND t.flag=1")
    int findCountTeacherBySearchVO(String search);


}
