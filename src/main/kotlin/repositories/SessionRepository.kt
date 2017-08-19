package io.awilson.timein.repositories

import io.awilson.timein.domain.Session
import org.springframework.data.repository.CrudRepository

interface SessionRepository : CrudRepository<Session, Int>
