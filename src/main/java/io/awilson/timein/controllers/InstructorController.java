package io.awilson.timein.controllers;

import io.awilson.timein.domain.Instructor;
import io.awilson.timein.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Provide a Controller for the Instructor class.
 */
@Controller
public class InstructorController {

    private InstructorService instructorService;

    @Autowired
    public void setInstructorService(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @RequestMapping("instructor/new")
    public String newInstructor(Model model){
        model.addAttribute("instructor", new Instructor());
        return "instructorform";
    }

    @RequestMapping(value = "instructor", method = RequestMethod.POST)
    public String saveInstructor(Instructor instructor){
        instructorService.saveInstructor(instructor);
        return "redirect:/instructor/" + instructor.getId();
    }

    @RequestMapping("instructor/{id}")
    public String showInstructor(@PathVariable Integer id, Model model){
        model.addAttribute("instructor", instructorService.getInstructorById(id));
        return "instructorshow";
    }

    @RequestMapping(value = "/instructors", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("instructors", instructorService.listAllInstructors());
        return "instructors";
    }

    @RequestMapping("instructor/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("instructor", instructorService.getInstructorById(id));
        return "instructorform";
    }

    @RequestMapping("instructor/delete/{id}")
    public String delete(@PathVariable Integer id){
        instructorService.deleteInstructor(id);
        return "redirect:/instructors";
    }
}