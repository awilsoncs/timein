package io.awilson.timein.services;

import io.awilson.timein.domain.Instructor;
import io.awilson.timein.repositories.InstructorRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Setter(onMethod = @_(@Autowired))
    private InstructorRepository instructorRepository;

    @Override
    public Iterable<Instructor> listAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Integer id) {
        return instructorRepository.findOne(id);
    }

    @Override
    public Instructor saveInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @Override
    public void deleteInstructor(Integer id) {
        instructorRepository.delete(id);
    }
}