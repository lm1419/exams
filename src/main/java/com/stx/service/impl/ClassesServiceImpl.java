package com.stx.service.impl;

import com.stx.dao.ClassesMapper;
import com.stx.entity.Classes;
import com.stx.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper mapper;

    @Override
    public List<Classes> findClassesByProfessId(Integer professid) {
        List<Classes> byProfessId = mapper.findByProfessId(professid);
        return byProfessId;
    }
}
