package com.stx.service;

import com.stx.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员的业务操作接口
 */
public interface AdminService {

    /**
     * 添加一个管理员的方法
     * @param admin
     */
    void addAdmin(Admin admin);

    /**
     * 根据ID删除一个管理员
     * @param id
     */
    void delAdminById(Integer id);

    /**
     * 根据ID查找一个管理员
     * @param id
     * @return
     */
    Admin findAdminById(Integer id);

    /**
     * 返回一个管理员列表
     * @return
     */
    List<Admin> findAllAdmin();

    /**
     * 修改管理员用户名与密码的方法
     * @param admin
     */
    void updateAdmin(Admin admin);

    /**
     * 登录方法
     * @param admin
     * @return
     */
    Admin findAdminByIdAndPassWord(Admin admin);

}
