package io.awilson.timein.controllers

import io.awilson.timein.domain.Student
import io.awilson.timein.services.CourseService
import io.awilson.timein.services.InstructorService
import io.awilson.timein.services.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Provide a Controller for the Student class.
 */
@Controller
class StudentController {

    @Autowired
    lateinit var studentService: StudentService
    @Autowired
    lateinit var instructorService: InstructorService
    @Autowired
    lateinit var courseService: CourseService

    @RequestMapping("student/new")
    fun newStudent(model: Model): String {
        model.addAttribute("instructors", instructorService.listAllInstructors())
        model.addAttribute("courses", courseService.listAll())
        model.addAttribute("student", Student())
        return "studentform"
    }

    @RequestMapping(value = "student", method = arrayOf(RequestMethod.POST))
    fun saveStudent(student: Student): String {
        studentService.saveStudent(student)
        return "redirect:/student/" + student.id
    }

    @RequestMapping("student/{id}")
    fun showStudent(@PathVariable id: Int, model: Model): String {
        model.addAttribute("student", studentService.getStudentById(id))
        return "studentshow"
    }

    @RequestMapping(value = "/students", method = arrayOf(RequestMethod.GET))
    fun list(model: Model): String {
        model.addAttribute("students", studentService.listAllStudents())
        return "students"
    }

    @RequestMapping("student/edit/{id}")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("instructors", instructorService.listAllInstructors())
        model.addAttribute("courses", courseService.listAll())
        model.addAttribute("student", studentService.getStudentById(id))
        return "studentform"
    }

    @RequestMapping("student/delete/{id}")
    fun delete(@PathVariable id: Int): String {
        studentService.deleteStudent(id)
        return "redirect:/students"
    }

    @RequestMapping("student/{id}/login")
    fun login(@PathVariable id: Int): String {
        studentService.login(id)
        return "redirect:/students"
    }

    @RequestMapping("student/{id}/logout")
    fun logout(@PathVariable id: Int): String {
        studentService.logout(id)
        return "redirect:/students"
    }
}
