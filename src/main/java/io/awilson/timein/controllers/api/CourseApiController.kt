package io.awilson.timein.controllers.api

import io.awilson.timein.domain.Course
import io.awilson.timein.services.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/courses")
class CourseApiController {
    @Autowired lateinit var service: CourseService

    /**
     * Provide a get mapping endpoint for single Courses
     */
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Course> {
        val course : Course = service.getCourseById(id) ?: throw Exception("Could not find Course with id:$id")
        return ResponseEntity<Course>(course, HttpStatus.OK)
    }

    /**
     * Provide a GET mapping endpoint for all Courses
     */
    @GetMapping
    fun get(): ResponseEntity<Iterable<Course>> {
        val courses: Iterable<Course> = service.listAllCourses()
        return ResponseEntity<Iterable<Course>>(courses, HttpStatus.OK)
    }

    /**
     * Provide a POST endpoint for Courses
     */
    @PostMapping
    fun post(@RequestBody course: Course): ResponseEntity<Course> {
        val updated = service.saveCourse(course)
        return ResponseEntity<Course>(updated, HttpStatus.OK)
    }

    /**
     * Provide a DELETE endpoint for Courses
     */
    @DeleteMapping
    fun delete(@RequestParam(value="id", defaultValue = "0") id: Int): HttpStatus {
        service.deleteCourse(id)
        return HttpStatus.OK
    }
}
