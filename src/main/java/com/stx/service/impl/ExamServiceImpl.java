package com.stx.service.impl;

import com.stx.dao.ExamMapper;
import com.stx.entity.Exam;
import com.stx.entity.PageShell;
import com.stx.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    ExamMapper mapper;

    @Override
    public String findExamByExamDateWhichAndClassRomm(Exam exam) {
        List<Exam> exams = mapper.findExamByExamDateWhichAndClassRomm(exam);
        System.out.println("result==>" + exams);
        if (exams == null || exams.isEmpty()) {
            return "empty";
        } else {
            return "distinct";
        }
    }

    @Override
    public void addExam(Exam exam) {
        if (exam.getExamdate().getTime() > System.currentTimeMillis()) {
            exam.setFlag(1);
        } else {
            exam.setFlag(0);
        }
        System.out.println(exam);
        mapper.addExam(exam);
    }


    @Override
    public Exam findById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public void update(Exam exam) {
        if (exam.getExamdate().getTime() > System.currentTimeMillis()) {
            exam.setFlag(1);
        } else {
            exam.setFlag(0);
        }
        mapper.update(exam);
    }

    @Override
    public void del(Integer id) {
        mapper.del(id);
    }

    @Override
    public PageShell searchExam(int rows, int currentPage) {
        PageShell pageShell = new PageShell();
        int totalCount = 0;
        pageShell.setCurrentPage(currentPage);
        pageShell.setRows(rows);
        List<Exam> all = mapper.findAll((currentPage-1)*rows,rows);
        totalCount = mapper.findTotalCount();
        pageShell.setDatas(all);
        int totalPage = totalCount / rows;
        if (totalCount % rows != 0) {
            totalPage = totalPage + 1;
        }
        pageShell.setTotalPage(totalPage);
        return pageShell;
    }
}
