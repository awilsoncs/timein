package io.awilson.timein.repositories;

import io.awilson.timein.domain.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer> {}
