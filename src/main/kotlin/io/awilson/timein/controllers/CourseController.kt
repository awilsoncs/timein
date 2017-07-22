package io.awilson.timein.controllers

import io.awilson.timein.domain.Course
import io.awilson.timein.repositories.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * Provide a Controller for the Course class.
 */
@Controller
@RequestMapping("site/courses")
class CourseController {

    @Autowired
    lateinit var repo: CourseRepository

    @RequestMapping("/new")
    fun new(model: Model): String {
        model.addAttribute("course", Course())
        return "courseform"
    }

    @PostMapping
    fun post(course: Course): String {
        repo.save(course)
        return "redirect:/site/courses/" + course.id
    }

    @RequestMapping("/{id}")
    fun show(@PathVariable id: Int, model: Model): String {
        model.addAttribute("course", repo.findOne(id))
        return "courseshow"
    }

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("courses", repo.findAll())
        return "courses"
    }

    @RequestMapping("/{id}/edit")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("course", repo.findOne(id))
        return "courseform"
    }

    @RequestMapping("/{id}/delete")
    fun delete(@PathVariable id: Int): String {
        repo.delete(id)
        return "redirect:/site/courses"
    }
}
