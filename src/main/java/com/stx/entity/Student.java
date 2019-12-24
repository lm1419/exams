package com.stx.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Student {
    private Integer studentid;
    private String name;
    private String sex;
    private Classes classes;
    private Date birthday;
    private String address;
    private String politapperance;
    private String note;
    private String password;
    private int flag;
}
