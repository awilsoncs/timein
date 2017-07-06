package io.awilson.timein.controllers.api

import io.awilson.timein.domain.Student
import io.awilson.timein.services.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * RESTful api endpoints for Students
 */
@RestController
@RequestMapping("/api/Students")
class StudentApiController {
    @Autowired lateinit var service: StudentService

    /**
     * Provide a get mapping endpoint for single Students
     */
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Student> {
        val Student : Student = service.getStudentById(id) ?: throw Exception("Could not find Student with id:$id")
        return ResponseEntity(Student, HttpStatus.OK)
    }

    /**
     * Provide a GET mapping endpoint for all Students
     */
    @GetMapping
    fun get(): ResponseEntity<Iterable<Student>> {
        val Students: Iterable<Student> = service.listAllStudents()
        return ResponseEntity(Students, HttpStatus.OK)
    }

    /**
     * Provide a POST endpoint for Students
     */
    @PostMapping
    fun post(@RequestBody Student: Student): ResponseEntity<Student> {
        val updated = service.saveStudent(Student)
        return ResponseEntity(updated, HttpStatus.OK)
    }

    /**
     * Provide a POST mapping for logging a student in.
     */
    @PostMapping("/{id}/login")
    fun postLogin(@PathVariable id: Int): HttpStatus {
        service.login(id)
        return HttpStatus.OK
    }

    /**
     * Provide a POST mapping for logging a student out.
     */
    @PostMapping("/{id}/logout")
    fun postLogout(@PathVariable id: Int): HttpStatus {
        service.logout(id)
        return HttpStatus.OK
    }

    /**
     * Provide a DELETE endpoint for Students
     */
    @DeleteMapping
    fun delete(@RequestParam(value="id", defaultValue = "0") id: Int): HttpStatus {
        service.deleteStudent(id)
        return HttpStatus.OK
    }
}