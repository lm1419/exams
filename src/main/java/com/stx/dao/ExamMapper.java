package com.stx.dao;

import com.stx.entity.Exam;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ExamMapper {

    @Select("SELECT * FROM exam WHERE examdate=#{examdate} AND which = #{which} AND classroomid=#{classroom.id} AND flag = 1")
    List<Exam> findExamByExamDateWhichAndClassRomm(Exam exam);

    @Insert("INSERT INTO exam (classesid,courseid,examdate,which,classroomid,flag) VALUES(#{classes.classesId},#{course.courseid},#{examdate},#{which},#{classroom.id},#{flag})")
    void addExam(Exam exam);

    @Results(id = "examMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "examdate",property = "examdate"),
            @Result(column = "which",property = "which"),
            @Result(column = "courseid",property = "course",one = @One(select = "com.stx.dao.CourseMapper.findCourseById",fetchType = FetchType.EAGER)),
            @Result(column = "classesid",property = "classes",one = @One(select = "com.stx.dao.ClassesMapper.findById",fetchType = FetchType.EAGER)),
            @Result(column = "classroomid",property = "classroom",one = @One(select = "com.stx.dao.ClassRoomMapper.findById",fetchType = FetchType.EAGER))
    })
    @Select("SELECT * FROM exam WHERE flag = 1 ORDER BY examdate DESC LIMIT #{start},#{rows}")
    List<Exam> findAll(int start,int rows);

    @Select("SELECT COUNT(id) FROM exam WHERE flag=1")
    Integer findTotalCount();

    @ResultMap("examMap")
    @Select("SELECT * FROM exam WHERE flag = 1 AND id=#{id}")
    Exam findById(Integer id);

    @Update("UPDATE exam SET examdate=#{examdate},which=#{which},classroomid=#{classroom.id},flag=#{flag} WHERE id=#{id}")
    void update(Exam exam);

    @Select("UPDATE exam SET flag = 0 WHERE id = #{id}")
    void del(Integer id);
}
