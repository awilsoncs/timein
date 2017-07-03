package io.awilson.timein.repositories

import io.awilson.timein.domain.Instructor
import org.springframework.data.repository.CrudRepository

interface InstructorRepository : CrudRepository<Instructor, Int>
