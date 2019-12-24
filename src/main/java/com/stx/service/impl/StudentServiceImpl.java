package com.stx.service.impl;

import com.stx.dao.StudentMapper;
import com.stx.entity.PageShell;
import com.stx.entity.Student;
import com.stx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper mapper;

    @Override
    public void addStudent(Student student) {
        mapper.addStudent(student);
    }

    @Override
    public void delStudent(Integer studentid) {
        mapper.delStudent(studentid);
    }

    @Override
    public void updateStudent(Student student) {
        mapper.updateStudent(student);
    }


    @Override
    public Student findStudentById(Integer id) {
        List<Student> studentById = mapper.findStudentById(id);
        if (studentById.isEmpty()) {
            return null;
        }
        return studentById.get(0);
    }

    @Override
    public Student findStudentByIdAndPassWord(Student student) {
        Student studentByIdAndPassWord = mapper.findStudentByIdAndPassWord(student);
        return studentByIdAndPassWord;
    }

    @Override
    public PageShell searchStudent(int rows, int currentPage, String search) {
        PageShell pageShell = new PageShell();
        int totalCount = 0;
        pageShell.setCurrentPage(currentPage);
        pageShell.setRows(rows);
        if ("".equals(search)||search==null) {
            List<Student> all = mapper.findAll(rows*(currentPage-1),rows);
            totalCount = mapper.findTotalCount();
            pageShell.setDatas(all);
        } else {
            search = "%" + search + "%";
            List<Student> studentBySearch = mapper.findStudentBySearch(search, rows * (currentPage - 1), rows);
            totalCount = mapper.findCountStudentBySearchVO(search);
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
