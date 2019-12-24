package com.stx.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Grade {
    private Integer id;
    private Course course;
    private Date courdate;
    private Student student;
    private int flag;
    private int grade;
}
