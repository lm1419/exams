package com.stx.service;

import com.stx.entity.PageShell;
import com.stx.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    void addTeacher(Teacher teacher);

    void delTeacher(Integer teacherid);

    void updateTeacher(Teacher teacher);

    Teacher findTeacherById(Integer teacherid);

    Teacher findTeacherByIdAndPassword(Teacher teacher);

    PageShell searchTeacher(int rows, int currentPage, String search);
}
