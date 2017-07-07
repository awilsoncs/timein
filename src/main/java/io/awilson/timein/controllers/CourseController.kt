package io.awilson.timein.controllers

import io.awilson.timein.domain.Course
import io.awilson.timein.services.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * Provide a Controller for the Course class.
 */
@Controller
@RequestMapping("/courses")
class CourseController {

    @Autowired
    lateinit var courseService: CourseService

    @RequestMapping("/new")
    fun new(model: Model): String {
        model.addAttribute("course", Course())
        return "courseform"
    }

    @PostMapping
    fun post(course: Course): String {
        courseService.save(course)
        return "redirect:/course/" + course.id
    }

    @RequestMapping("/{id}")
    fun show(@PathVariable id: Int, model: Model): String {
        model.addAttribute("course", courseService.getById(id))
        return "courseshow"
    }

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("courses", courseService.listAll())
        return "courses"
    }

    @RequestMapping("/{id}/edit")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("course", courseService.getById(id))
        return "courseform"
    }

    @RequestMapping("/{id}/delete")
    fun delete(@PathVariable id: Int): String {
        courseService.delete(id)
        return "redirect:/courses"
    }
}