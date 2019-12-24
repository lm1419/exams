package com.stx.service;


import com.stx.entity.Course;
import com.stx.entity.PageShell;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseService {

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    void addCourse(Course course);

    void delById(Integer courseid);

    Course findCourseById(Integer courseid);

    void updateCourse(Course course);

    void delDesign(Integer courseid,Integer professid);

    void addDesign(Course course);

    List<Course> findByProfessid(Integer professid);

    PageShell searchCourse(int rows, int currentPage, String search);
}
