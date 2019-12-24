package com.stx.controller;

import com.stx.entity.*;
import com.stx.service.CourseService;
import com.stx.service.DeptService;
import com.stx.service.ProfessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ProfessService professService;

    @Autowired
    private DeptService deptService;

    @GetMapping("add")
    public String goToAddPage(Model model) {
        List<Dept> allDept = deptService.findAllDept();
        model.addAttribute("depts", allDept);
        return "admin/course_add";
    }

    @PostMapping("add")
    public String addCourse(Course course) {
        courseService.addCourse(course);
        return "redirect:/course/add";
    }


    @RequestMapping("del/{courseid}")
    public String delCourse(@PathVariable Integer courseid) {
        courseService.delById(courseid);
        return "redirect:/course/getList/10/1";
    }

    @RequestMapping("dels/{rows}/{currentPage}")
    public String delCourse(@PathVariable Integer rows, @PathVariable Integer currentPage, Integer[] ck) {
        for (Integer id : ck) {
            courseService.delById(id);
        }
        return "redirect:/course/getList/"+rows+"/"+currentPage;
    }

    @RequestMapping("design/{courseid}")
    public String design(@PathVariable Integer courseid, Model model) {
        List<Profess> professes = professService.findByCourseNo(courseid);
        Course course = courseService.findCourseById(courseid);
        model.addAttribute("course", course);
        model.addAttribute("professes", professes);
        return "admin/course_design";
    }

    @RequestMapping("deldesign/{courseid}/{professid}")
    public String delDesign(@PathVariable Integer courseid, @PathVariable Integer professid) {
        courseService.delDesign(courseid, professid);
        return "redirect:/course/design/" + courseid;
    }

    @GetMapping("adddesign/{courseid}")
    public String gotoAddDesignPage(Model model, @PathVariable Integer courseid) {
        Course course = courseService.findCourseById(courseid);
        List<Dept> allDept = deptService.findAllDept();
        model.addAttribute("depts", allDept);
        model.addAttribute("course", course);
        return "admin/course_design_add";
    }

    @PostMapping("adddesign")
    public String AddDesign(Course course) {
        courseService.addDesign(course);
        return "redirect:/course/design/" + course.getCourseid();
    }

    @RequestMapping("getList/{rows}/{currentPage}")
    public String a(Model model, HttpSession session, @PathVariable int rows, @PathVariable int currentPage, String search) {
        PageShell pageShell;
        if (search == null) {
            //说明是单纯进入或点击了下一页
        } else if (search.length() > 0) {
            //说明其中有值，将值放入Session
            session.setAttribute("searchcourse", search);
        } else if ("".equals(search)) {
            //说明是空串，是表单提交了，但是其中没值，移除Session
            session.removeAttribute("searchcourse");
        }
        //说明字符串为null，代表单纯地点击了下一页或正常访问
        if (session.getAttribute("searchcourse") != null) {
            //说明session中有值，是模糊查询的下一页或正常访问,从session中获得模糊查询字段。
            search = (String) session.getAttribute("searchcourse");
            pageShell = courseService.searchCourse(rows, currentPage, search);
        } else {
            //说明session里没有值，是精确查询的下一页或正常访问
            pageShell = courseService.searchCourse(rows, currentPage, search);
        }
        List<Student> list = pageShell.getDatas();
        model.addAttribute("ps", pageShell);
        model.addAttribute("courses", list);
        return "/admin/course_manager";
    }


}
