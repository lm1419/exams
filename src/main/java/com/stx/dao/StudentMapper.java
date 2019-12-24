package com.stx.dao;

import com.stx.entity.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface StudentMapper {

    @Insert("INSERT INTO STUDENT (name,sex,classesid,birthday,address,politapperance,note,password,flag) VALUES(#{name},#{sex},#{classes.classesId}, #{birthday},#{address},#{politapperance},#{note},#{password},1)")
    void addStudent(Student student);

    @Update("UPDATE student SET flag = 0 WHERE studentid = #{studentid}")
    void delStudent(Integer studentid);

    @Update("UPDATE student SET studentid=#{studentid},name=#{name},sex=#{sex},classesId=#{classes.classesId},birthday=#{birthday},address=#{address},politapperance=#{politapperance},note=#{note},password=#{password},flag=1 WHERE studentid=#{studentid}")
    void updateStudent(Student student);

    @Results(id = "studentMap", value = {
            @Result(property = "studentid", column = "studentid"),
            @Result(property = "name", column = "name"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "address", column = "address"),
            @Result(property = "politapperance", column = "politapperance"),
            @Result(property = "note", column = "note"),
            @Result(property = "password", column = "password"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "classes", column = "classesid", one = @One(select = "com.stx.dao.ClassesMapper.findByIdND", fetchType = FetchType.EAGER))
    }
    )
    @Select("SELECT * FROM student WHERE flag = 1 LIMIT #{start},#{rows}")
    List<Student> findAll(int start,int rows);


    @ResultMap("studentMap")
    @Select("SELECT * FROM student WHERE flag = 1 AND studentid = #{studentid}")
    List<Student> findStudentById(Integer id);

    @ResultMap("studentMap")
    @Select("SELECT * FROM student WHERE flag = 1 AND studentid = #{studentid} AND password = #{password}")
    Student findStudentByIdAndPassWord(Student student);

    @ResultMap("studentMap")
    @Select("SELECT * FROM student WHERE flag = 1 AND classesid = #{classesid}")
    Student findStudentByClassesId(Integer classesid);

    @Select("SELECT COUNT(studentid) FROM student WHERE flag = 1")
    int findTotalCount();

    @ResultMap("studentMap")
    @Select("SELECT * FROM student s,classes c WHERE s.classesid=c.classid AND(s.name LIKE #{search} OR c.classname LIKE #{search} OR s.address LIKE #{search} OR s.note LIKE #{search}) AND s.studentid IS NOT NULL AND s.flag=1 LIMIT #{start},#{rows}")
    List<Student> findStudentBySearch(String search,int start,int rows);

    @Select("SELECT COUNT(*) FROM student s,classes c WHERE s.classesid=c.classid AND(s.name LIKE #{search} OR c.classname LIKE #{search} OR s.address LIKE #{search} OR s.note LIKE #{search}) AND s.studentid IS NOT NULL AND s.flag=1")
    int findCountStudentBySearchVO(String search);

}
