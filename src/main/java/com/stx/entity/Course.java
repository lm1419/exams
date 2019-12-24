package com.stx.entity;

import lombok.Data;

@Data
public class Course {
    Integer courseid;
    String coursename;
    Integer coursescore;
    String introduce;
    Profess profess;

}
