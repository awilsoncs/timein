package io.awilson.timein.controllers

import io.awilson.timein.domain.Instructor
import io.awilson.timein.repositories.InstructorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Provide a Controller for the Instructor class.
 */
@Controller
@RequestMapping("site/instructors")
class InstructorController {

    @Autowired
    lateinit var repo: InstructorRepository

    @RequestMapping("/new")
    fun newInstructor(model: Model): String {
        model.addAttribute("instructor", Instructor())
        return "instructorform"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun saveInstructor(instructor: Instructor): String {
        repo.save(instructor)
        return "redirect:/site/instructors/" + instructor.id
    }

    @RequestMapping("/{id}")
    fun showInstructor(@PathVariable id: Int, model: Model): String {
        model.addAttribute("instructor", repo.findOne(id))
        return "instructorshow"
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun list(model: Model): String {
        model.addAttribute("instructors", repo.findAll())
        return "instructors"
    }

    @RequestMapping("/{id}/edit")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("instructor", repo.findOne(id))
        return "instructorform"
    }

    @RequestMapping("/{id}/delete")
    fun delete(@PathVariable id: Int): String {
        repo.delete(id)
        return "redirect:/site/instructors"
    }
}