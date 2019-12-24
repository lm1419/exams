package com.stx.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Profess {
    private Integer professId;
    private String professName;
    private Dept dept;
    private List<Classes> classes;
    private Integer flag;
    private List<Course> courses;
}
