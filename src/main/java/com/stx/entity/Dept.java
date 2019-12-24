package com.stx.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class Dept {
    Integer deptno;
    String deptname;
    Integer flag;
    List<Profess> professes;
}
