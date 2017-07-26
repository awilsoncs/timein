package io.awilson.timein.reports;

import io.awilson.timein.domain.Session;
import io.awilson.timein.domain.Student;
import lombok.extern.java.Log;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.previous;

/**
 * Provide reports that roll up Session Durations based on criteria function and groups by Student.
 */
@Log
public class CumulativeTimeReport {

    private Map<Student, Duration> studentDurations;

    /**
     * Roll up the session durations
     * @param sessions An iterable of Sessions
     * @param filter A filter.
     */
    public CumulativeTimeReport(Iterable<Session> sessions, Predicate<Session> filter) {
        log.info("Generating Cumulative Time report.");
        Stream<Session> sessionStream = StreamSupport.stream(sessions.spliterator(), false);

        studentDurations = sessionStream
                .filter(filter)
                .collect(Collectors.groupingBy(Session::getStudent,
                        Collectors.reducing(Duration.ZERO, Session::getDuration, Duration::plus)));
    }

    /**
     * Constructor for all sessions.
     * @param sessions
     */
    public CumulativeTimeReport(Iterable<Session> sessions) {
        this(sessions, x -> true);
    }

    /**
     * Return all time for this week.
     */
    public static CumulativeTimeReport getWeeklyTimeReport(Iterable<Session> sessions) {
        LocalDate today = LocalDate.now();
        return getTimeReportForWeekOf(sessions, today);
    }

    /**
     * Return all time for a given week.
     * @param sessions
     * @param localDate
     * @return
     */
    public static CumulativeTimeReport getTimeReportForWeekOf(Iterable<Session> sessions, LocalDate localDate) {
        LocalDate lastSunday = localDate.with(previous(SUNDAY));
        LocalDate nextSunday = localDate.with(next(SUNDAY));
        return new CumulativeTimeReport(
                sessions,
                x -> x.getDate().isAfter(lastSunday) && x.getDate().isBefore(nextSunday)
        );
    }

    public Map<Student, Duration> getStudentDurations() {
        return this.studentDurations;
    }
}
