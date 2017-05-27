package io.awilson.timein.bootstrap;

import io.awilson.timein.domain.Course;
import io.awilson.timein.domain.Instructor;
import io.awilson.timein.domain.Session;
import io.awilson.timein.domain.Student;
import io.awilson.timein.repositories.CourseRepository;
import io.awilson.timein.repositories.InstructorRepository;
import io.awilson.timein.repositories.SessionRepository;
import io.awilson.timein.repositories.StudentRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Log
@Component
public class StudentLoader implements ApplicationListener<ContextRefreshedEvent>{

    private InstructorRepository instructorRepository;
    private StudentRepository studentRepository;
    private SessionRepository sessionRepository;
    private CourseRepository courseRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Autowired
    public void setInstructorRepository(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Instructor instructor = new Instructor();
        instructor.setFirstName("Yo");
        instructor.setLastName("Derkson");
        instructorRepository.save(instructor);

        Course course = new Course();
        course.setTitle("Dumb School");
        courseRepository.save(course);

        Student testA = new Student();
        testA.setFirstName("Test");
        testA.setLastName("Testerson");
        testA.setCourse(course);
        testA.setInstructor(instructor);
        studentRepository.save(testA);

        log.info("Saved Student - id: " + testA.getId());

        Session session = new Session();
        session.setStudent(testA);
        session.setStart(Instant.now());
        session.setEnd(Instant.now());
        sessionRepository.save(session);
    }
}
