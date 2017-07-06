package io.awilson.timein.services

import io.awilson.timein.domain.Session

interface SessionService {
    fun listAllSessions(): Iterable<Session>

    fun getSessionById(id: Int): Session

    fun saveSession(session: Session): Session

    fun deleteSession(id: Int)
}
