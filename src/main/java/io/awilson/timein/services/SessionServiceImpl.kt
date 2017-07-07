package io.awilson.timein.services

import io.awilson.timein.domain.Session
import io.awilson.timein.repositories.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SessionServiceImpl : SessionService {

    @Autowired
    lateinit var repo: SessionRepository

    override fun listAllSessions(): Iterable<Session> = repo.findAll()

    override fun getSessionById(id: Int): Session = repo.findOne(id) ?: throw Exception("Session:$id not found")

    override fun saveSession(session: Session): Session = repo.save(session)

    override fun deleteSession(id: Int) = repo.delete(id)
}