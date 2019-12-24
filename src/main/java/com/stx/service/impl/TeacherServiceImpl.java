package com.stx.service.impl;

import com.stx.dao.TeacherMapper;
import com.stx.entity.PageShell;
import com.stx.entity.Student;
import com.stx.entity.Teacher;
import com.stx.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper mapper;

    @Override
    public void addTeacher(Teacher teacher) {
        mapper.addTeacher(teacher);
    }

    @Override
    public void delTeacher(Integer teacherid) {
        mapper.delTeacher(teacherid);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        mapper.updateTeacher(teacher);
    }

    @Override
    public Teacher findTeacherById(Integer teacherid) {
        Teacher teacherById = mapper.findTeacherById(teacherid);
        return teacherById;
    }

    @Override
    public Teacher findTeacherByIdAndPassword(Teacher teacher) {
        teacher.setFlag(1);
        Teacher teacherByIdAndPassword = mapper.findTeacherByIdAndPassword(teacher);
        return teacherByIdAndPassword;
    }

    @Override
    public PageShell searchTeacher(int rows, int currentPage, String search) {
        PageShell pageShell = new PageShell();
        int totalCount = 0;
        pageShell.setCurrentPage(currentPage);
        pageShell.setRows(rows);
        if ("".equals(search)||search==null) {
            List<Teacher> all =  mapper.findAll(rows*(currentPage-1),rows);
            totalCount = mapper.findTotalCount();
            pageShell.setDatas(all);
        } else {
            search = "%" + search + "%";
            List<Teacher> teachers = mapper.findTeacherBySearch(search, rows * (currentPage - 1), rows);
            totalCount = mapper.findCountTeacherBySearchVO(search);
            pageShell.setDatas(teachers);
        }
        int totalPage = totalCount / rows;
        if (totalCount % rows != 0) {
            totalPage = totalPage + 1;
        }

        pageShell.setTotalPage(totalPage);
        return pageShell;
    }
}
