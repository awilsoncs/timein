package io.awilson.timein.repositories

import io.awilson.timein.domain.Student
import org.springframework.data.repository.CrudRepository

interface StudentRepository : CrudRepository<Student, Int>
