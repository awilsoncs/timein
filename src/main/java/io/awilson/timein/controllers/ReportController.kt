package io.awilson.timein.controllers

import io.awilson.timein.reports.CumulativeTimeReport
import io.awilson.timein.services.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ReportController {

    @Autowired
    lateinit var sessionService: SessionService

    @RequestMapping("report/cumulative")
    fun showCumulativeTime(model: Model): String {
        val cumulativeTimeReport = CumulativeTimeReport(sessionService.listAllSessions())
        model.addAttribute("records", cumulativeTimeReport.studentDurations)
        return "cumulativetime"
    }

    @RequestMapping("report/weekly")
    fun showWeeklyTime(model: Model): String {
        val cumulativeTimeReport = CumulativeTimeReport.getWeeklyTimeReport(sessionService.listAllSessions())
        model.addAttribute("records", cumulativeTimeReport.studentDurations)
        return "cumulativetime"
    }
}
