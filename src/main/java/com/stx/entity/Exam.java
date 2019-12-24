package com.stx.entity;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Date;


@Data
public class Exam {
    private Integer id;
    private Classes classes;
    private Course course;
    private Date examdate;
    private String which;
    private ClassRoom classroom;
    private Integer flag;
}
