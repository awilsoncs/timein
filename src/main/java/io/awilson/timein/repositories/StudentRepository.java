package io.awilson.timein.repositories;

import io.awilson.timein.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer>{}
