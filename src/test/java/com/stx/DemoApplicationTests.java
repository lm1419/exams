package com.stx;

import com.stx.dao.*;
import com.stx.entity.*;
import com.stx.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    AdminMapper mapper;
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    DeptMapper deptMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    GradeMapper gradeMapper;

    @Autowired
    ProfessMapper professMapper;

//    @Test
//    void contextLoads() {
//        Admin admin = new Admin();
//        admin.setUsername("伟件长");
//        admin.setId(2);
//        admin.setPassword("123ab");
////        mapper.addAdmin(admin);
////        mapper.delAdminById(1);
////        Admin adminById = mapper.findAdminById(1);
////        System.out.println(adminById);
//        mapper.updateAdmin(admin);
//        List<Admin> allAdmin = mapper.findAllAdmin();
//        System.out.println(allAdmin);
//    }

    @Test
    void testCourse() {
//        Course course = new Course();
//        course.setCoursename("伟件网通史");
//        course.setCoursescore(4);
//        course.setCourseid(3);
//        courseMapper.updateCourse(course);
////        courseMapper.delById(1);
//        Admin fb = courseMapper.findCourseById(1);
//        System.out.println(fb);
//        List<Course> allCourse = courseMapper.findAllCourse();
//        System.out.println(allCourse);
    }

    @Test
    void testDept() {
//        Dept dept = new Dept();
//        dept.setDeptname("慧件");
//        dept.setDeptno(3);
//        deptMapper.addDept(dept);
//        deptMapper.delDeptById(4);
//        deptMapper.updateDept(dept);
//        List<Dept> allDept = deptMapper.findAllDept();
//        Dept deptById = deptMapper.findDeptById(1);
//        System.out.println(deptById);
    }

    @Test
    void testStudent() {
//        Student student = new Student();
//        student.setName("马建斌");
//        student.setAddress("远景学院");
//        student.setBirthday(new Date());
//        Dept dept = new Dept();
//        dept.setDeptno(3);
//        student.setDept(dept);
//        student.setPolitapperance("平民");
//        student.setPassword("123");
//        student.setSex("匹夫");
//        student.setNote("无名小卒");
////        System.out.println(student);
//        List<Student> i = studentMapper.findStudentById(1);
//        System.out.println(i);
    }

    @Test
    void testTeacher() {
//        Teacher teacher = new Teacher();
//        teacher.setName("刘勇");
//        teacher.setSex("男");
//        teacher.setBirthday(new Date());
//        teacher.setPolitapperance("色狼");
//        Dept dept = new Dept();
//        dept.setDeptno(3);
//        teacher.setDept(dept);
//        teacher.setStudyhistory("淫棍");
//        teacher.setPtitle("色狼");
//        teacher.setPassword("");
//        teacher.setTeacherid(3);
//        teacherMapper.updateTeacher(teacher);
//        teacherMapper.delTeacher(5);
        Teacher teacherById = teacherMapper.findTeacherById(4);
        System.out.println(teacherById);
    }

    @Autowired
    DataSource dataSource;

    @Test
    void gradeTeac2her() {
        List<Profess> byCourseNo = professMapper.findByCourseNo(1);
        System.out.println(dataSource);
    }


    @Test
    void gradeTeacher() {
        int num = 1;
        int numn = 1;
        for (int i = 10; i < 100; i++) {
            Teacher teacher = new Teacher();
            teacher.setTeacherid(i);
//                Student student = new Student();
//                student.setStudentid(i);
            if (i % 2 == 0) {
                teacher.setName("李奥六" + num + "号");
                teacher.setSex("男");
                teacher.setPassword("123");
                teacher.setPolitapperance("中共党员");
                teacher.setBirthday(new Date());
                teacher.setPtitle("助教");
                Dept dept = new Dept();
                dept.setDeptno(new Random().nextInt(8) + 1);
                teacher.setDept(dept);
                teacher.setStudyhistory("硕士");
                num = num + 1;
            } else {
                teacher.setName("孙老师" + numn + "号");
                teacher.setSex("女");
                teacher.setPassword("123");
                teacher.setPolitapperance("共青团员");
                teacher.setBirthday(new Date());
                teacher.setPtitle("高工");
                Dept dept = new Dept();
                dept.setDeptno(new Random().nextInt(8) + 1);
                teacher.setDept(dept);
                teacher.setStudyhistory("硕士");
                numn = numn + 1;

            }
            teacherMapper.addTeacher(teacher);
        }

    }
}
