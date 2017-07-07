package io.awilson.timein.controllers.api

import io.awilson.timein.domain.Session
import io.awilson.timein.services.SessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * RESTful api endpoints for Sessions
 */
@RestController
@RequestMapping("/api/sessions")
class SessionApiController {
    @Autowired lateinit var service: SessionService

    /**
     * Provide a get mapping endpoint for single Sessions
     */
    @GetMapping("/{id}")
    fun get(@PathVariable id: Int): ResponseEntity<Session> {
        val Session : Session = service.getSessionById(id)
        return ResponseEntity(Session, HttpStatus.OK)
    }

    /**
     * Provide a GET mapping endpoint for all Sessions
     */
    @GetMapping
    fun get(): ResponseEntity<Iterable<Session>> = ResponseEntity(service.listAllSessions(), HttpStatus.OK)

    /**
     * Provide a POST endpoint for Sessions
     */
    @PostMapping
    fun post(@RequestBody Session: Session): ResponseEntity<Session> {
        return ResponseEntity(service.saveSession(Session), HttpStatus.OK)
    }

    /**
     * Provide a DELETE endpoint for Sessions
     */
    @DeleteMapping
    fun delete(@RequestParam(value="id", defaultValue = "0") id: Int): HttpStatus {
        service.deleteSession(id)
        return HttpStatus.OK
    }
}