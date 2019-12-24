package com.stx.controller;

import com.stx.dao.TeacherMapper;
import com.stx.entity.Dept;
import com.stx.entity.PageShell;
import com.stx.entity.Student;
import com.stx.entity.Teacher;
import com.stx.service.DeptService;
import com.stx.service.TeacherService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@SessionAttributes({"teacher"})
@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService service;

    @Autowired
    DeptService deptService;

    private PageShell pageShell;

    @RequestMapping("login")
    public String teacherLogin(Teacher teacher, Model model) {
        Teacher teacherByIdAndPassword = service.findTeacherByIdAndPassword(teacher);
        if (teacherByIdAndPassword == null) {
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
        model.addAttribute("teacher", teacherByIdAndPassword);
        return "redirect:/main_teacher.html";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("add")
    public String gotoAddPage(HttpSession session) {
        List<Dept> allDept = deptService.findAllDept();
        session.setAttribute("depts", allDept);
        return "admin/teacher_add";
    }

    @PostMapping("add")
    public String addTeacher(Teacher teacher) {
        service.addTeacher(teacher);
        return "redirect:/teacher/getList/10/1";
    }


    @RequestMapping("getList/{rows}/{currentPage}")
    public String a(Model model,HttpSession session,@PathVariable int rows,@PathVariable int currentPage,String search){
        if (search==null){
            //说明是单纯进入或点击了下一页
        }else if (search.length()>0){
            //说明其中有值，将值放入Session
            session.setAttribute("searchteacher",search);
        }else if("".equals(search)){
            //说明是空串，是表单提交了，但是其中没值，移除Session
            session.removeAttribute("searchteacher");
        }
        //说明字符串为null，代表单纯地点击了下一页或正常访问
        if (session.getAttribute("searchteacher")!=null){
            //说明session中有值，是模糊查询的下一页或正常访问,从session中获得模糊查询字段。
            search=(String)session.getAttribute("searchteacher");
            pageShell = service.searchTeacher(rows, currentPage, search);
        }else {
            //说明session里没有值，是精确查询的下一页或正常访问
            pageShell = service.searchTeacher(rows, currentPage, search);
        }
        List<Teacher> list = pageShell.getDatas();
        model.addAttribute("ps",pageShell);
        model.addAttribute("teachers",list);
        return "/admin/teacher_managers";
    }

    @RequestMapping("del/{teacherid}")
    public String delTeacher(@PathVariable Integer teacherid) {
        service.delTeacher(teacherid);
        return "redirect:/teacher/getList/10/1";
    }

    @RequestMapping("dels/{rows}/{currentPage}")
    public String delChoose(Integer[] ck, @PathVariable Integer rows, @PathVariable Integer currentPage) {
        if (ck == null) {
            return "redirect:/teacher/getList/" + rows + "/" + currentPage;

        }
        for (Integer id : ck) {
            service.delTeacher(id);
        }
        return "redirect:/teacher/getList/10/1";
    }

    @GetMapping("update/{teacherid}")
    public String goToUpdate(@PathVariable Integer teacherid, Model model, HttpSession session) {
        Teacher teacherById = service.findTeacherById(teacherid);
        List<Dept> allDept = deptService.findAllDept();
        session.setAttribute("depts", allDept);
        model.addAttribute("teacher", teacherById);
        return "admin/teacher_update";
    }

    @PostMapping("update")
    public String goToUpdatePost(Teacher teacher) {
        service.updateTeacher(teacher);
        return "redirect:/teacher/getList/10/1";
    }

    @RequestMapping("gotoTeacher/{teacherid}")
    public String gotoStudent(@PathVariable Integer teacherid, Model model) {
        Teacher teacher = service.findTeacherById(teacherid);
        model.addAttribute(teacher);
        return "/admin/teacher_inform";
    }


}
