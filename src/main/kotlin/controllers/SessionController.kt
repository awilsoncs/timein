package io.awilson.timein.controllers

import io.awilson.timein.domain.Session
import io.awilson.timein.services.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

/**
 * Provide a Controller for the Session class.
 */
@Controller
class SessionController {

    @Autowired
    lateinit var sessionService: SessionService

    @RequestMapping("session/new")
    fun newSession(model: Model): String {
        model.addAttribute("session", Session())
        return "sessionform"
    }

    @RequestMapping(value = "session", method = arrayOf(RequestMethod.POST))
    fun saveSession(session: Session): String {
        sessionService.saveSession(session)
        return "redirect:/session/" + session.id
    }

    @RequestMapping("session/{id}")
    fun showSession(@PathVariable id: Int, model: Model): String {
        model.addAttribute("session", sessionService.getSessionById(id))
        return "sessionshow"
    }

    @RequestMapping(value = "/sessions", method = arrayOf(RequestMethod.GET))
    fun list(model: Model): String {
        model.addAttribute("sessions", sessionService.listAllSessions())
        return "sessions"
    }

    @RequestMapping("session/edit/{id}")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("session", sessionService.getSessionById(id))
        return "sessionform"
    }

    @RequestMapping("session/delete/{id}")
    fun delete(@PathVariable id: Int): String {
        sessionService.deleteSession(id)
        return "redirect:/sessions"
    }
}