package com.stx.controller;

import com.stx.entity.Admin;
import com.stx.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService service;

    /**
     * 管理员登录
     * @param admin
     * @param
     * @return
     */
    @RequestMapping("logins")
    public String adminLogin(Admin admin, HttpServletRequest request) {
        Admin adminByIdAndPassWord = service.findAdminByIdAndPassWord(admin);
        if (adminByIdAndPassWord != null) {
            request.getSession().setAttribute("admin",adminByIdAndPassWord);
            return "redirect:/main_admin.html";
        }
        request.setAttribute("msg", "用户名或密码错误");
        return "admin/login";
    }

    /**
     * 管理员退出登录，清除session，跳回登录页面
     * @param request
     * @return
     */
    @RequestMapping("exitlogin")
    public String adminExitLogin(HttpServletRequest request, HttpServletResponse response) {
        //将Session中的变量全部清空
        HttpSession session = request.getSession();
        session.invalidate();
        return "admin/login";
    }
}
