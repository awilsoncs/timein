package io.awilson.timein.services

import io.awilson.timein.domain.Session
import io.awilson.timein.repositories.SessionRepository
import lombok.Setter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SessionServiceImpl : SessionService {

    @Autowired
    lateinit var sessionRepository: SessionRepository

    override fun listAllSessions(): Iterable<Session> = sessionRepository.findAll()

    override fun getSessionById(id: Int): Session = sessionRepository.findOne(id)

    override fun saveSession(session: Session): Session = sessionRepository.save(session)

    override fun deleteSession(id: Int) = sessionRepository.delete(id)
}