package io.awilson.timein.reports;

import io.awilson.timein.domain.Session;
import io.awilson.timein.domain.Student;

import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static java.time.temporal.TemporalAdjusters.previous;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Provide reports that roll up Session durations based on criteria function and groups by Student.
 */
public class CumulativeTimeReport {

    @Getter
    private Map<Student, Duration> studentDurations;

    /**
     * Roll up the session durations
     * @param sessions An iterable of Sessions
     * @param filter A filter.
     */
    public CumulativeTimeReport(Iterable<Session> sessions, Predicate<Session> filter) {
        Stream<Session> sessionStream = StreamSupport.stream(sessions.spliterator(), false);

        //Filter based on criteria function, then roll up Durations and group by Student.
        studentDurations = sessionStream
                .filter(filter)
                .collect(Collectors.groupingBy(Session::getStudent,
                        Collectors.reducing(Duration.ZERO, Session::getDuration, Duration::plus)));
    }

    public CumulativeTimeReport(Iterable<Session> sessions) {
        this(sessions, x -> true);
    }

    /**
     * Return all time for this week.
     */
    public static CumulativeTimeReport getWeeklyTimeReport(Iterable<Session> sessions) {
        LocalDate today = LocalDate.now();
        LocalDate lastSunday = today.with(previous(SUNDAY));
        LocalDate nextSunday = today.with(next(SUNDAY));
        return new CumulativeTimeReport(
                sessions,
                x -> x.getDay().isAfter(lastSunday) && x.getDay().isBefore(nextSunday)
        );
    }
}
