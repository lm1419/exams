package com.stx.service;

import com.stx.entity.Profess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProfessService {

    List<Profess> findByDeptno(Integer deptno);

    List<Profess> findById(Integer professid);

    List<Profess> findByCourseNo(Integer courseId);


}
