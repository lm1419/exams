package com.stx.service.impl;

import com.stx.dao.ProfessMapper;
import com.stx.entity.Profess;
import com.stx.service.ProfessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessServiceImpl implements ProfessService {

    @Autowired
    private ProfessMapper mapper;

    @Override
    public List<Profess> findByDeptno(Integer deptno) {
        return mapper.findByDeptno(deptno);
    }

    @Override
    public List<Profess> findById(Integer professid) {
        return mapper.findById(professid);
    }

    @Override
    public List<Profess> findByCourseNo(Integer courseId) {
        return mapper.findByCourseNo(courseId);
    }


}
