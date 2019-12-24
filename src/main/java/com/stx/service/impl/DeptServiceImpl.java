package com.stx.service.impl;

import com.stx.dao.DeptMapper;
import com.stx.entity.Dept;
import com.stx.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void addDept(Dept dept) {
        deptMapper.addDept(dept);
    }

    @Override
    public void delDeptById(Integer deptno) {
        deptMapper.delDeptById(deptno);
    }

    @Override
    public Dept findDeptById(Integer deptno) {
        Dept deptById = deptMapper.findDeptById(deptno);
        return deptById;
    }

    @Override
    public List<Dept> findAllDept() {
        List<Dept> allDept = deptMapper.findAllDept();
        if (allDept.isEmpty()) {
            return null;
        }
        return allDept;
    }

    @Override
    public void updateDept(Dept dept) {
        deptMapper.updateDept(dept);
    }
}
