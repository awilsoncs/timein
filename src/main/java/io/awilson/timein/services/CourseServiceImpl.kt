package io.awilson.timein.services

import io.awilson.timein.domain.Course
import io.awilson.timein.repositories.CourseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl : CourseService {

    @Autowired
    lateinit var repo: CourseRepository

    override fun listAll(): Iterable<Course> = repo.findAll()

    override fun getById(id: Int): Course = repo.findOne(id) ?: throw Exception("Course:$id not found")

    override fun save(course: Course): Course = repo.save(course)

    override fun delete(id: Int) = repo.delete(id)
}