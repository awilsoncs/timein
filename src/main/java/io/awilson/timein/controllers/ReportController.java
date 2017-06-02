package io.awilson.timein.controllers;

import io.awilson.timein.domain.Session;
import io.awilson.timein.services.CourseService;
import io.awilson.timein.services.InstructorService;
import io.awilson.timein.services.SessionService;
import io.awilson.timein.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController {

    private CourseService courseService;
    private InstructorService instructorService;
    private SessionService sessionService;
    private StudentService studentService;

    @Autowired
    public ReportController(CourseService courseService,
                            InstructorService instructorService,
                            SessionService sessionService,
                            StudentService studentService){
        this.courseService = courseService;
        this.instructorService = instructorService;
        this.sessionService = sessionService;
        this.studentService = studentService;
    }

    @RequestMapping("report/cumulative-time")
    public String showCumulativeTime(Model model){

        return "cumulativetime";
    }
}
