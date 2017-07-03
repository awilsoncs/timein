package io.awilson.timein.controllers;

import io.awilson.timein.reports.CumulativeTimeReport;
import io.awilson.timein.services.CourseService;
import io.awilson.timein.services.InstructorService;
import io.awilson.timein.services.SessionService;
import io.awilson.timein.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReportController {

    @Setter(onMethod = @_(@Autowired))
    private SessionService sessionService;

    @RequestMapping("report/cumulative")
    public String showCumulativeTime(Model model){
        CumulativeTimeReport cumulativeTimeReport = new CumulativeTimeReport(sessionService.listAllSessions());
        model.addAttribute("records", cumulativeTimeReport.getStudentDurations());
        return "cumulativetime";
    }

    @RequestMapping("report/weekly")
    public String showWeeklyTime(Model model){
        CumulativeTimeReport cumulativeTimeReport =
                CumulativeTimeReport.getWeeklyTimeReport(sessionService.listAllSessions());
        model.addAttribute("records", cumulativeTimeReport.getStudentDurations());
        return "cumulativetime";
    }
}
