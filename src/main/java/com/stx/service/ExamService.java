package com.stx.service;

import com.stx.entity.Exam;
import com.stx.entity.PageShell;

import java.util.List;

public interface ExamService {
    public String findExamByExamDateWhichAndClassRomm(Exam exam);

    void addExam(Exam exam);


    Exam findById(Integer id);

    void update(Exam exam);

    void del(Integer id);

    PageShell searchExam(int rows, int currentPage);


}
