package com.stx.service;


import com.stx.entity.Classes;

import java.util.List;

public interface ClassesService {

    List<Classes> findClassesByProfessId(Integer professid);
}
