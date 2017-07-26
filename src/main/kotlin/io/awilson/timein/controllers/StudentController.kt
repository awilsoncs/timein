package io.awilson.timein.controllers

import io.awilson.timein.domain.Student
import io.awilson.timein.repositories.CourseRepository
import io.awilson.timein.repositories.InstructorRepository
import io.awilson.timein.repositories.SessionRepository
import io.awilson.timein.repositories.StudentRepository
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
@RequestMapping("/site/students")
class StudentController {

    @Autowired
    lateinit var studentRepo: StudentRepository
    @Autowired
    lateinit var instructorRepo: InstructorRepository
    @Autowired
    lateinit var courseRepo: CourseRepository
    @Autowired
    lateinit var sessionRepo: SessionRepository

    @RequestMapping("/new")
    fun newStudent(model: Model): String {
        model.addAttribute("instructors", instructorRepo.findAll())
        model.addAttribute("courses", courseRepo.findAll())
        model.addAttribute("student", Student())
        return "studentform"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun saveStudent(student: Student): String {
        studentRepo.save(student)
        return "redirect:/student/" + student.id
    }

    @RequestMapping("/{id}")
    fun showStudent(@PathVariable id: Int, model: Model): String {
        model.addAttribute("student", studentRepo.findOne(id))
        return "studentshow"
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun list(model: Model): String {
        model.addAttribute("students", studentRepo.findAll())
        return "students"
    }

    @RequestMapping("/{id}/edit")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("instructors", instructorRepo.findAll())
        model.addAttribute("courses", courseRepo.findAll())
        model.addAttribute("student", studentRepo.findOne(id))
        return "studentform"
    }

    @RequestMapping("/{id}/delete")
    fun delete(@PathVariable id: Int): String {
        studentRepo.delete(id)
        return "redirect:/site/students"
    }

    @RequestMapping("/{id}/login")
    fun login(@PathVariable id: Int): String {
        val student: Student = studentRepo.findOne(id)
        sessionRepo.save(student.login())
        studentRepo.save(student)
        return "redirect:/site/students"
    }

    @RequestMapping("/{id}/logout")
    fun logout(@PathVariable id: Int): String {
        val student: Student = studentRepo.findOne(id)
        sessionRepo.save(student.logout())
        studentRepo.save(student)
        return "redirect:/site/students"
    }
}
