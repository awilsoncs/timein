package io.awilson.timein.reports;

import io.awilson.timein.domain.Session;
import io.awilson.timein.domain.Student;
import lombok.Getter;
import lombok.extern.java.Log;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Provide reports that roll up Session Durations based on criteria function and groups by Student and Week in a
 * in a crosstab format.
 */
@Log
public class WeeklyTimeCrossTabReport {
    @Getter
    private Map<LocalDate, Map<Student, Duration>> studentDurationsByWeek;

    public WeeklyTimeCrossTabReport(Iterable<Session> sessions, LocalDate startDate, LocalDate endDate) {
        log.info("Generating Weekly Time crosstab report.");
        Stream<Session> sessionStream = StreamSupport.stream(sessions.spliterator(), false);

    }
}
