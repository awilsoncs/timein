package io.awilson.timein.repositories

import io.awilson.timein.domain.Course
import org.springframework.data.repository.PagingAndSortingRepository

interface CourseRepository : PagingAndSortingRepository<Course, Int>