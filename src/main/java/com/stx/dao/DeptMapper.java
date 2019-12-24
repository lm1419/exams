package com.stx.dao;

import com.stx.entity.Admin;
import com.stx.entity.Dept;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Insert("INSERT INTO dept (deptname,flag) VALUES(#{deptname},1)")
    void addDept(Dept dept);

    @Update("UPDATE dept SET flag = 0 WHERE deptno = #{deptno}")
    void delDeptById(Integer id);

    @Results(id = "deptMap",value = {
            @Result(property = "deptno",column = "deptno"),
            @Result(property = "deptname",column = "deptname"),
            @Result(property = "professes",column = "deptno",many = @Many(
                    select = "com.stx.dao.ProfessMapper.findByDeptno",
                    fetchType = FetchType.LAZY
            ))
    })
    @Select("SELECT * FROM dept WHERE flag=1")
    List<Dept> findAllDept();

    @ResultMap("deptMap")
    @Select("SELECT * FROM dept WHERE deptno = #{deptno} AND flag=1")
    Dept findDeptById(Integer id);

    @Select("SELECT * FROM dept WHERE deptno = #{deptno} AND flag=1")
    Dept findDeptByIdND(Integer deptno);

    @Update("UPDATE dept SET deptname=#{deptname} WHERE deptno = #{deptno} AND flag=1")
    void updateDept(Dept dept);


}
