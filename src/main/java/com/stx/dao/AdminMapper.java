package com.stx.dao;

import com.stx.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Insert("INSERT INTO admin (username,password,flag) VALUES(#{username},#{password},1) ")
    void addAdmin(Admin admin);

    @Update("UPDATE admin SET flag = 0 WHERE id = #{id}")
    void delAdminById(Integer id);

    @Select("SELECT * FROM admin WHERE id = #{id} AND flag=1")
    Admin findAdminById(Integer id);

    @Select("SELECT * FROM admin WHERE flag = 1")
    List<Admin> findAllAdmin();

    @Select("SELECT * FROM admin WHERE id = #{id} AND password = #{password} AND flag=1")
    Admin findAdminByIdAndPassWord(Admin admin);

    @Update("UPDATE admin SET username=#{username},password=#{password} WHERE id = #{id}")
    void updateAdmin(Admin admin);


}
