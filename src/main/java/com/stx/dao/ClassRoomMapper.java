package com.stx.dao;

import com.stx.entity.ClassRoom;
import com.stx.entity.Exam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassRoomMapper {

    @Select("SELECT * FROM classroom")
    List<ClassRoom> findAll();

    @Select("SELECT DISTINCT building FROM classroom")
    List<Integer> findAllBuilding();

    @Select("SELECT * FROM classroom WHERE building = #{building}")
    List<ClassRoom> findByBuilding(Integer building);

    @Select("SELECT * FROM classroom WHERE id = #{id}")
    ClassRoom findById(Integer id);
}
