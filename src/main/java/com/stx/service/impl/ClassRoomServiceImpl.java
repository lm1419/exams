package com.stx.service.impl;

import com.stx.dao.ClassRoomMapper;
import com.stx.entity.ClassRoom;
import com.stx.service.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassRoomServiceImpl implements ClassRoomService {

    @Autowired
    ClassRoomMapper classRoomMapper;

    @Override
    public List<ClassRoom> findAllClassRoom() {
        List<ClassRoom> all = classRoomMapper.findAll();
        return all;
    }

    @Override
    public List<Integer> findAllBuilding() {
        List<Integer> allBuilding = classRoomMapper.findAllBuilding();
        return allBuilding;
    }

    @Override
    public List<ClassRoom> findByBuilding(Integer building) {
        List<ClassRoom> byBuilding = classRoomMapper.findByBuilding(building);
        return byBuilding;
    }
}
