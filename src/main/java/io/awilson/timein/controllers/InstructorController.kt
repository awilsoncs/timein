package io.awilson.timein.controllers

import io.awilson.timein.domain.Instructor
import io.awilson.timein.services.InstructorService
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
class InstructorController {

    @Autowired
    lateinit var instructorService: InstructorService

    @RequestMapping("instructor/new")
    fun newInstructor(model: Model): String {
        model.addAttribute("instructor", Instructor())
        return "instructorform"
    }

    @RequestMapping(value = "instructor", method = arrayOf(RequestMethod.POST))
    fun saveInstructor(instructor: Instructor): String {
        instructorService.saveInstructor(instructor)
        return "redirect:/instructor/" + instructor.id
    }

    @RequestMapping("instructor/{id}")
    fun showInstructor(@PathVariable id: Int, model: Model): String {
        model.addAttribute("instructor", instructorService.getInstructorById(id))
        return "instructorshow"
    }

    @RequestMapping(value = "/instructors", method = arrayOf(RequestMethod.GET))
    fun list(model: Model): String {
        model.addAttribute("instructors", instructorService.listAllInstructors())
        return "instructors"
    }

    @RequestMapping("instructor/edit/{id}")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("instructor", instructorService.getInstructorById(id))
        return "instructorform"
    }

    @RequestMapping("instructor/delete/{id}")
    fun delete(@PathVariable id: Int): String {
        instructorService.deleteInstructor(id)
        return "redirect:/instructors"
    }
}