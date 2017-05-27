package io.awilson.timein.repositories;

import io.awilson.timein.domain.Instructor;
import org.springframework.data.repository.CrudRepository;

public interface InstructorRepository extends CrudRepository<Instructor, Integer> {}
