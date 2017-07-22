package io.awilson.timein.controllers

import io.awilson.timein.domain.Session
import io.awilson.timein.repositories.SessionRepository
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
@RequestMapping("/site/sessions")
class SessionController {

    @Autowired
    lateinit var repo: SessionRepository

    @RequestMapping("/new")
    fun newSession(model: Model): String {
        model.addAttribute("session", Session())
        return "sessionform"
    }

    @RequestMapping(method = arrayOf(RequestMethod.POST))
    fun saveSession(session: Session): String {
        repo.save(session)
        return "redirect:/site/sessions/" + session.id
    }

    @RequestMapping("/{id}")
    fun showSession(@PathVariable id: Int, model: Model): String {
        model.addAttribute("session", repo.findOne(id))
        return "sessionshow"
    }

    @RequestMapping(method = arrayOf(RequestMethod.GET))
    fun list(model: Model): String {
        model.addAttribute("sessions", repo.findAll())
        return "sessions"
    }

    @RequestMapping("/{id}/edit")
    fun edit(@PathVariable id: Int, model: Model): String {
        model.addAttribute("session", repo.findOne(id))
        return "sessionform"
    }

    @RequestMapping("/{id}/delete")
    fun delete(@PathVariable id: Int): String {
        repo.delete(id)
        return "redirect:/site/sessions"
    }
}