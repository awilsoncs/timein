package io.awilson.timein.repositories

import io.awilson.timein.domain.Instructor
import org.springframework.data.repository.PagingAndSortingRepository

interface InstructorRepository : PagingAndSortingRepository<Instructor, Int>
