package io.awilson.timein.services;

import io.awilson.timein.domain.Session;
import io.awilson.timein.domain.Student;
import io.awilson.timein.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @_(@Autowired))
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    private SessionService sessionService;

    @Override
    public Iterable<Student> listAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer id) {
        return studentRepository.findOne(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentRepository.delete(id);
    }

    @Override
    public void login(Integer id) {
        Student student = getStudentById(id);
        Session session = new Session();
        session.setStudent(student);
        sessionService.saveSession(session);

        student.setCurrentSession(session);
        studentRepository.save(student);
    }

    @Override
    public void logout(Integer id) {
        Student student = getStudentById(id);
        Session session = student.getCurrentSession();
        if(session == null) {
            return;
        } else {
            session.close();
            sessionService.saveSession(session);
            student.setCurrentSession(null);
            studentRepository.save(student);
        }
    }
}
