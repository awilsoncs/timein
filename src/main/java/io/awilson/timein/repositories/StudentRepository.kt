package io.awilson.timein.repositories

import io.awilson.timein.domain.Student
import org.springframework.data.repository.PagingAndSortingRepository

interface StudentRepository : PagingAndSortingRepository<Student, Int>
