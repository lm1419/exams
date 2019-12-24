package com.stx.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class MyHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object admin = request.getSession().getAttribute("admin");
//        Object student = request.getSession().getAttribute("student");
//        Object teacher = request.getSession().getAttribute("teacher");
        if (admin == null) {
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/login.html").forward(request, response);
            return false;
        }else {
            return true;
        }

    }
}
