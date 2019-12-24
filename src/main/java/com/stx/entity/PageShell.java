package com.stx.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageShell {
    List datas;
    Integer totalPage;
    Integer rows;
    Integer currentPage;
}
