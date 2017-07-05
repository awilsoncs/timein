package io.awilson.timein.controllers.api

import io.awilson.timein.domain.Course
import io.awilson.timein.services.CourseService
import lombok.extern.log4j.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/courses")
class CourseApiController {
    @Autowired lateinit var service: CourseService

    @GetMapping
    fun get(@RequestParam(value="id", defaultValue = "0") id: Int): ResponseEntity<Course> {
        val course : Course = service.getCourseById(id) ?: throw Exception("Could not find Course with id:$id")
        return ResponseEntity<Course>(course, HttpStatus.OK)
    }

    @PostMapping
    fun post(@RequestBody course: Course): ResponseEntity<Course> {
        val updated = service.saveCourse(course)
        return ResponseEntity<Course>(updated, HttpStatus.OK)
    }

    @DeleteMapping
    fun delete(@RequestParam(value="id", defaultValue = "0") id: Int): HttpStatus {
        service.deleteCourse(id)
        return HttpStatus.OK
    }
}
