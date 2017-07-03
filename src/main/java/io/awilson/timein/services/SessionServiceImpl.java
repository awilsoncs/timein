package io.awilson.timein.services;

import io.awilson.timein.domain.Session;
import io.awilson.timein.repositories.SessionRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    @Setter(onMethod = @_(@Autowired))
    private SessionRepository sessionRepository;

    @Override
    public Iterable<Session> listAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session getSessionById(Integer id) {
        return sessionRepository.findOne(id);
    }

    @Override
    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public void deleteSession(Integer id) {
        sessionRepository.delete(id);
    }
}