package io.awilson.timein.reports;

import io.awilson.timein.domain.Session;
import io.awilson.timein.domain.Student;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Provide reports that roll up Session Durations based on criteria function and groups by Student and Week in a
 * in a crosstab format.
 */
public class WeeklyTimeCrossTabReport {
    @Getter
    private Map<LocalDate, Map<Student, Duration>> studentDurationsByWeek;

    public WeeklyTimeCrossTabReport(Iterable<Session> sessions, LocalDate startDate, LocalDate endDate) {
        Stream<Session> sessionStream = StreamSupport.stream(sessions.spliterator(), false);

    }
}
