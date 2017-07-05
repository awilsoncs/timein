package io.awilson.timein.services

import io.awilson.timein.domain.Course

interface CourseService {
    fun listAllCourses(): Iterable<Course>

    fun getCourseById(id: Int): Course?

    fun saveCourse(course: Course): Course

    fun deleteCourse(id: Int)
}
