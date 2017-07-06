package io.awilson.timein.controllers.api

import io.awilson.timein.domain.Instructor
import io.awilson.timein.services.InstructorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * RESTful api endpoints for Instructors
 */
@RestController
@RequestMapping("/api/instructors")
class InstructorApiController {
    @Autowired lateinit var service: InstructorService

    /**
     * Provide a get mapping endpoint for single Instructors
     */
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Instructor> {
        val instructor : Instructor = service.getInstructorById(id) ?: throw Exception("Could not find Instructor with id:$id")
        return ResponseEntity(instructor, HttpStatus.OK)
    }

    /**
     * Provide a GET mapping endpoint for all Instructors
     */
    @GetMapping
    fun get(): ResponseEntity<Iterable<Instructor>> {
        val Instructors: Iterable<Instructor> = service.listAllInstructors()
        return ResponseEntity(Instructors, HttpStatus.OK)
    }

    /**
     * Provide a POST endpoint for Instructors
     */
    @PostMapping
    fun post(@RequestBody Instructor: Instructor): ResponseEntity<Instructor> {
        val updated = service.saveInstructor(Instructor)
        return ResponseEntity(updated, HttpStatus.OK)
    }

    /**
     * Provide a DELETE endpoint for Instructors
     */
    @DeleteMapping
    fun delete(@RequestParam(value="id", defaultValue = "0") id: Int): HttpStatus {
        service.deleteInstructor(id)
        return HttpStatus.OK
    }
}