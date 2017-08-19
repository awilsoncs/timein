package io.awilson.timein.controllers

import io.awilson.timein.services.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @Autowired
    lateinit var service: StudentService

    @GetMapping("/")
    fun list(model: Model): String {
        model.addAttribute("students", service.listActiveStudents())
        return "students"
    }
}
