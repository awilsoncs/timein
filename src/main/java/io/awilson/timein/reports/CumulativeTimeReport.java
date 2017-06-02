package io.awilson.timein.reports;

import io.awilson.timein.domain.Session;
import io.awilson.timein.domain.Student;
import io.awilson.timein.services.StudentService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CumulativeTimeReport {

    @Getter
    private Map<Student, Duration> studentDurations;
    private StudentService studentService;

    public CumulativeTimeReport(Iterable<Student> students) {
        studentDurations = new HashMap<>();

        for(Student student : students) {
            studentDurations.put(student,
                    student.getSessions()
                            .stream()
                            .map(Session::getDuration)
                            .reduce(Duration::plus)
                            .get());
        }
    }
}
