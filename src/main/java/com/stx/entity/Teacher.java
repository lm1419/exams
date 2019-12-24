package com.stx.entity;

import lombok.Data;

import java.util.Date;


@Data
public class Teacher {
    private Integer teacherid;
    private String name;
    private String sex;
    private Date birthday;
    private Dept dept;
    private String ptitle;
    private String studyhistory;
    private String politapperance;
    private String password;
    private int count;
    private int flag;
}
