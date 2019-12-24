package com.stx.service;

import com.stx.entity.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeptService {

    void addDept(Dept dept);

    void delDeptById(Integer id);

    Dept findDeptById(Integer id);

    List<Dept> findAllDept();

    void updateDept(Dept dept);
}
