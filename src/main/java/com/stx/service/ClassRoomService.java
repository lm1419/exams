package com.stx.service;


import com.stx.entity.ClassRoom;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClassRoomService {

    List<ClassRoom> findAllClassRoom();

    List<Integer> findAllBuilding();

    List<ClassRoom> findByBuilding(Integer building);
}
