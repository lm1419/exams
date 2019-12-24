package com.stx.service.impl;

import com.stx.dao.AdminMapper;
import com.stx.entity.Admin;
import com.stx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public void addAdmin(Admin admin) {
        adminMapper.addAdmin(admin);
    }

    @Override
    public void delAdminById(Integer id) {
        adminMapper.delAdminById(id);
    }

    @Override
    public Admin findAdminById(Integer id) {
        Admin admin = adminMapper.findAdminById(id);
        return admin;
    }

    @Override
    public List<Admin> findAllAdmin() {
        List<Admin> adminList = adminMapper.findAllAdmin();
        if (adminList.isEmpty()) {
            return null;
        }
        return adminList;
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    @Override
    public Admin findAdminByIdAndPassWord(Admin admin) {
        admin.setFlag(1);
        Admin loginAdmin = adminMapper.findAdminByIdAndPassWord(admin);
        return loginAdmin;
    }
}
