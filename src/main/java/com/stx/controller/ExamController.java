package com.stx.controller;

import com.stx.entity.*;
import com.stx.service.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("exam")
public class ExamController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ExamService examService;

    @Autowired
    private ProfessService professService;

    @Autowired
    private ClassesService classesService;


    @GetMapping("add")
    public String gotoAddPage(Model model, HttpSession session){
        List<Dept> depts = deptService.findAllDept();
        List<Integer> buildings = classRoomService.findAllBuilding();
        session.setAttribute("depts",depts);
        model.addAttribute("buildings",buildings);
        return "admin/exams_add";
    }

    @PostMapping("add")
    public String addExam(Exam exam){
        examService.addExam(exam);
        return "admin/exams_add";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @ResponseBody
    @RequestMapping("getCourse")
    public List<Course> getCourse(Integer professid){
        List<Course> byProfessid = courseService.findByProfessid(professid);
        return byProfessid;
    }

    @ResponseBody
    @RequestMapping("findclassroom")
    public List<ClassRoom> findClassRoom(Integer building){
        List<ClassRoom> byBuilding = classRoomService.findByBuilding(building);
        return byBuilding;
    }

    @ResponseBody
    @RequestMapping("checkexamused")
    public Map<String,String> checkexamused(@RequestBody Exam exam){
        System.out.println(exam);
        HashMap<String, String> responseMap = new HashMap<>();
        String result = examService.findExamByExamDateWhichAndClassRomm(exam);
        responseMap.put("status", result);
        return responseMap;
    }



    @RequestMapping("getList/{rows}/{currentPage}")
    public String a(Model model, @PathVariable int rows, @PathVariable int currentPage) {
        PageShell pageShell = examService.searchExam(rows, currentPage);
        List<Exam> list = pageShell.getDatas();
        model.addAttribute("ps", pageShell);
        model.addAttribute("exams", list);
        return "/admin/exam_manager";
    }

    @GetMapping("update/{id}")
    public String updateExam(Model model,@PathVariable Integer id,HttpSession session){
        Exam byId = examService.findById(id);
        List<Profess> professes = professService.findByDeptno(byId.getClasses().getProfess().getDept().getDeptno());
        List<Classes> classes = classesService.findClassesByProfessId(byId.getClasses().getProfess().getProfessId());
        List<Course> courses = courseService.findByProfessid(byId.getClasses().getProfess().getProfessId());
        List<ClassRoom> rooms = classRoomService.findByBuilding(byId.getClassroom().getBuilding());
        model.addAttribute("id",id);
        model.addAttribute("exam",byId);
        List<Dept> depts = deptService.findAllDept();
        List<Integer> buildings = classRoomService.findAllBuilding();
        session.setAttribute("depts",depts);
        model.addAttribute("profess",professes);
        model.addAttribute("classes",classes);
        model.addAttribute("courses",courses);
        session.setAttribute("buildings",buildings);
        model.addAttribute("rooms",rooms);
        return "admin/exam_update";
    }

    @PostMapping("update")
    public String update(Exam exam){
        examService.update(exam);
        return "redirect:/exam/findAll";
    }

    @GetMapping("del/{id}")
    public String del(@PathVariable Integer id){
        examService.del(id);
        return "redirect:/exam/findAll";
    }

    @PostMapping("del")
    public  String dels(Integer[] ck){
        for (Integer id : ck) {
            examService.del(id);
        }
        return "redirect:/exam/findAll";
    }
}
