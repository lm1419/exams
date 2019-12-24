package com.stx.service.impl;

import com.stx.dao.CourseMapper;
import com.stx.entity.Course;
import com.stx.entity.PageShell;
import com.stx.entity.Student;
import com.stx.service.CourseService;
import org.apache.ibatis.annotations.One;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper mapper;

    @Override
    public void addCourse(Course course) {
        mapper.addCourse(course);
        mapper.addDesign(course);
    }

    @Override
    public void delById(Integer courseid) {
        mapper.delById(courseid);

    }

    @Override
    public Course findCourseById(Integer courseid) {
        return mapper.findCourseById(courseid);
    }

    @Override
    public void updateCourse(Course course) {
        mapper.updateCourse(course);
    }



    @Override
    public void delDesign(Integer courseid, Integer professid) {
        mapper.delDesign(courseid,professid);
    }

    @Override
    public void addDesign(Course course) {
        mapper.addDesign(course);
    }

    @Override
    public List<Course> findByProfessid(Integer professid) {
        return mapper.findByProfessId(professid);
    }


    @Override
    public PageShell searchCourse(int rows, int currentPage, String search) {
        PageShell pageShell = new PageShell();
        int totalCount = 0;
        pageShell.setCurrentPage(currentPage);
        pageShell.setRows(rows);
        if ("".equals(search)||search==null) {
            List<Course> all = mapper.findAllCourse((currentPage-1)*rows,rows);
            totalCount = mapper.findTotalCount();
            pageShell.setDatas(all);
        } else {
            search = "%" + search + "%";
            List<Course> studentBySearch = mapper.searchCourse(search, rows * (currentPage - 1), rows);
            totalCount = mapper.searchCount(search);
            pageShell.setDatas(studentBySearch);

        }
        int totalPage = totalCount / rows;
        if (totalCount % rows != 0) {
            totalPage = totalPage + 1;
        }

        pageShell.setTotalPage(totalPage);
        return pageShell;
    }

}
