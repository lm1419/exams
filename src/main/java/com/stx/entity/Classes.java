package com.stx.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Classes {
    private Integer classesId;
    private String classesName;
    private Profess profess;
    private Integer flag;
    private List<Student> students;

}
