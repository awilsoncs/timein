package io.awilson.timein.repositories

import io.awilson.timein.domain.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository : CrudRepository<Course, Int>
