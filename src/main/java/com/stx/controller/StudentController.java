package com.stx.controller;

import com.stx.entity.*;
import com.stx.service.*;
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

@Controller
@RequestMapping("student")
@SessionAttributes({"student"})

public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ProfessService professService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private CourseService courseService;

    private PageShell pageShell;

    @RequestMapping("login")
    public String studentLogin(Student student, Model model){
        Student studentByIdAndPassWord = studentService.findStudentByIdAndPassWord(student);
        if (studentByIdAndPassWord==null){
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
        model.addAttribute("student",studentByIdAndPassWord);
        return "redirect:/main_student.html";
    }

    @GetMapping("add")
    public String goToAddPage(HttpSession session){
        List<Dept> allDept = deptService.findAllDept();
        session.setAttribute("depts",allDept);
        return "admin/student_add";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @PostMapping("add")
    public String AddStudent( Student student){
        studentService.addStudent(student);
        return "redirect:/student/getList/10/1";
    }


    @RequestMapping("getList/{rows}/{currentPage}")
    public String a(Model model,HttpSession session,@PathVariable int rows,@PathVariable int currentPage,String search){
        if (search==null){
            //说明是单纯进入或点击了下一页
        }else if (search.length()>0){
            //说明其中有值，将值放入Session
            session.setAttribute("searchstudent",search);
        }else if("".equals(search)){
            //说明是空串，是表单提交了，但是其中没值，移除Session
            session.removeAttribute("searchstudent");
        }
        //说明字符串为null，代表单纯地点击了下一页或正常访问
        if (session.getAttribute("searchstudent")!=null){
            //说明session中有值，是模糊查询的下一页或正常访问,从session中获得模糊查询字段。
            search=(String)session.getAttribute("searchstudent");
            pageShell = studentService.searchStudent(rows, currentPage, search);
        }else {
            //说明session里没有值，是精确查询的下一页或正常访问
            pageShell = studentService.searchStudent(rows, currentPage, search);
        }
        List<Student> list = pageShell.getDatas();
        model.addAttribute("ps",pageShell);
        model.addAttribute("students",list);
        return "/admin/student_managers";
    }


    @RequestMapping("gotostudent/{studentid}")
    public String gotoStudent(@PathVariable Integer studentid,Model model){
        Student studentById = studentService.findStudentById(studentid);
        model.addAttribute(studentById);
        return "/admin/student_inform";
    }


    @GetMapping("getPro")
    @ResponseBody
    public List<Profess> getProfessByDeptNo(Integer deptno){
        List<Profess> byDeptno = professService.findByDeptno(deptno);
        return byDeptno;
    }

    @RequestMapping("getClasses")
    @ResponseBody
    public List<Classes> getClassesByProfessid(Integer professId){
        List<Classes> classesByProfessId = classesService.findClassesByProfessId(professId);
        return classesByProfessId;
    }

    @GetMapping("update/{studentid}")
    public String goToUpdatePage(Model model,@PathVariable Integer studentid){
        Student student = studentService.findStudentById(studentid);
        List<Dept> depts = deptService.findAllDept();
        List<Profess> professes = professService.findByDeptno(student.getClasses().getProfess().getDept().getDeptno());
        List<Classes> classeses = classesService.findClassesByProfessId(student.getClasses().getProfess().getProfessId());
        model.addAttribute("depts",depts);
        model.addAttribute("profess",professes);
        model.addAttribute("classes",classeses);
        model.addAttribute("student",student);
        return "admin/student_update";
    }

    @PostMapping("update")
    public String UpdateStudent(Student student){
        studentService.updateStudent(student);
        return "redirect:/student/getList/10/1";
    }

    @GetMapping("del/{studentid}")
    public String delStudent(Model model,@PathVariable Integer studentid){
        studentService.delStudent(studentid);
        return "redirect:/student/getList/10/1";
    }

    @RequestMapping("dels/{rows}/{currentPage}")
    public String delChoose(Integer[] ck,@PathVariable Integer rows,@PathVariable Integer currentPage){
        if (ck==null){
            return "redirect:/student/getList/"+rows+"/"+currentPage;

        }
        for (Integer id:ck){
            studentService.delStudent(id);
        }
        return "redirect:/student/getList/10/1";
    }
}
