package com.stx.service;

import com.stx.entity.PageShell;
import com.stx.entity.Student;
import java.util.List;
import java.util.Map;

public interface StudentService {
    void addStudent(Student student);

    void delStudent(Integer studentid);

    void updateStudent(Student student);

    Student findStudentById(Integer id);

    Student findStudentByIdAndPassWord(Student student);

    PageShell searchStudent(int rows, int currentPage, String search);
}
