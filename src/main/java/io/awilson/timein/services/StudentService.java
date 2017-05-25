package io.awilson.timein.services;

import io.awilson.timein.domain.Student;

public interface StudentService {
    Iterable<Student> listAllStudents();

    Student getStudentById(Integer id);

    Student saveStudent(Student student);

    void deleteStudent(Integer id);
}
