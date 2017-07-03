package io.awilson.timein.controllers

import io.awilson.timein.domain.Course
import io.awilson.timein.services.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Provide a Controller for the Course class.
 */
@Controller
class CourseController {

    @Autowired
    lateinit var courseService: CourseService

    @RequestMapping("course/new")
    fun newCourse(model: Model): String {
        model.addAttribute("course", Course())
        return "courseform"
    }

    @RequestMapping(value = "course", method = arrayOf(RequestMethod.POST))
    fun saveCourse(course: Course): String {
        courseService.saveCourse(course)
        return "redirect:/course/" + course.id
    }

    @RequestMapping("course/{id}")
    fun showCourse(@PathVariable id: Int, model: Model): String {
        model.addAttribute("course", courseService.getCourseById(id))
        return "courseshow"
    }

    @RequestMapping(value = "/courses", method = arrayOf(RequestMethod.GET))
    fun list(model: Model): String {
        model.addAttribute("courses", courseService.listAllCourses())
        return "courses"
    }

    @RequestMapping("course/edit/{id}")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("course", courseService.getCourseById(id))
        return "courseform"
    }

    @RequestMapping("course/delete/{id}")
    fun delete(@PathVariable id: Int): String {
        courseService.deleteCourse(id)
        return "redirect:/courses"
    }
}