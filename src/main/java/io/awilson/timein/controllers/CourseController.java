package io.awilson.timein.controllers;

import io.awilson.timein.domain.Course;
import io.awilson.timein.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Provide a Controller for the Course class.
 */
@Controller
public class CourseController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("course/new")
    public String newCourse(Model model){
        model.addAttribute("course", new Course());
        return "courseform";
    }

    @RequestMapping(value = "course", method = RequestMethod.POST)
    public String saveCourse(Course course){
        courseService.saveCourse(course);
        return "redirect:/course/" + course.getId();
    }

    @RequestMapping("course/{id}")
    public String showCourse(@PathVariable Integer id, Model model){
        model.addAttribute("course", courseService.getCourseById(id));
        return "courseshow";
    }

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("courses", courseService.listAllCourses());
        return "courses";
    }

    @RequestMapping("course/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("course", courseService.getCourseById(id));
        return "courseform";
    }

    @RequestMapping("course/delete/{id}")
    public String delete(@PathVariable Integer id){
        courseService.deleteCourse(id);
        return "redirect:/courses";
    }
}