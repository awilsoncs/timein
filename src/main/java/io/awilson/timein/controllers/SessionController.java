package io.awilson.timein.controllers;

import io.awilson.timein.domain.Session;
import io.awilson.timein.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Provide a Controller for the Session class.
 */
@Controller
public class SessionController {

    private SessionService sessionService;

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @RequestMapping("session/new")
    public String newSession(Model model){
        model.addAttribute("session", new Session());
        return "sessionform";
    }

    @RequestMapping(value = "session", method = RequestMethod.POST)
    public String saveSession(Session session){
        sessionService.saveSession(session);
        return "redirect:/session/" + session.getId();
    }

    @RequestMapping("session/{id}")
    public String showSession(@PathVariable Integer id, Model model){
        model.addAttribute("session", sessionService.getSessionById(id));
        return "sessionshow";
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("sessions", sessionService.listAllSessions());
        return "sessions";
    }

    @RequestMapping("session/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("session", sessionService.getSessionById(id));
        return "sessionform";
    }

    @RequestMapping("session/delete/{id}")
    public String delete(@PathVariable Integer id){
        sessionService.deleteSession(id);
        return "redirect:/sessions";
    }
}