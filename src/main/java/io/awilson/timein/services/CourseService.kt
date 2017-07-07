package io.awilson.timein.services

import io.awilson.timein.domain.Course

interface CourseService {
    fun listAll(): Iterable<Course>

    fun getById(id: Int): Course

    fun save(course: Course): Course

    fun delete(id: Int)
}
