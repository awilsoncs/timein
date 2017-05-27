package io.awilson.timein.services;

import io.awilson.timein.domain.Instructor;

public interface InstructorService {
    Iterable<Instructor> listAllInstructors();

    Instructor getInstructorById(Integer id);

    Instructor saveInstructor(Instructor instructor);

    void deleteInstructor(Integer id);
}
