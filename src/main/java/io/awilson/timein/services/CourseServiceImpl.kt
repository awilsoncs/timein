package io.awilson.timein.services

import io.awilson.timein.domain.Course
import io.awilson.timein.repositories.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl : CourseService {

    @Autowired
    lateinit var courseRepository: CourseRepository

    override fun listAllCourses(): Iterable<Course> = courseRepository.findAll()

    override fun getCourseById(id: Int): Course = courseRepository.findOne(id)
            ?: throw Exception("Course:$id not found")

    override fun saveCourse(course: Course): Course = courseRepository.save(course)

    override fun deleteCourse(id: Int) = courseRepository.delete(id)
}