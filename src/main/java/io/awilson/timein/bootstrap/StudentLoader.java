package io.awilson.timein.bootstrap;

import io.awilson.timein.domain.Session;
import io.awilson.timein.domain.Student;
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

    private StudentRepository studentRepository;
    private SessionRepository sessionRepository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setSessionRepository(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Student testA = new Student();
        testA.setFirstName("Test");
        testA.setLastName("Testerson");
        testA.setCourse("Fake Course");
        testA.setInstructor("Fake Instructor");
        studentRepository.save(testA);

        log.info("Saved Student - id: " + testA.getId());

        Session session = new Session();
        session.setStudent(testA);
        session.setStart(Instant.now());
        session.setEnd(Instant.now());
        sessionRepository.save(session);
    }
}
