package io.awilson.timein.controllers;

import io.awilson.timein.domain.Student;
import io.awilson.timein.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Provide a Controller for the Student class.
 */
@Controller
public class StudentController {

    private StudentService studentService;

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("student/new")
    public String newStudent(Model model){
        model.addAttribute("student", new Student());
        return "studentform";
    }

    @RequestMapping(value = "student", method = RequestMethod.POST)
    public String saveStudent(Student student){
        studentService.saveStudent(student);
        return "redirect:/student/" + student.getId();
    }

    @RequestMapping("student/{id}")
    public String showStudent(@PathVariable Integer id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "studentshow";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("students", studentService.listAllStudents());
        return "students";
    }

    @RequestMapping("student/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("student", studentService.getStudentById(id));
        return "studentform";
    }

    @RequestMapping("student/delete/{id}")
    public String delete(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @RequestMapping("student/{id}/login")
    public String login(@PathVariable Integer id){
        studentService.login(id);
        return "redirect:/students";
    }

    @RequestMapping("student/{id}/logout")
    public String logout(@PathVariable Integer id){
        studentService.logout(id);
        return "redirect:/students";
    }
}
