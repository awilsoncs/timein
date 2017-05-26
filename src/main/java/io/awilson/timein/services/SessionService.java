package io.awilson.timein.services;

import io.awilson.timein.domain.Session;

public interface SessionService {
    Iterable<Session> listAllSessions();

    Session getSessionById(Integer id);

    Session saveSession(Session session);

    void deleteSession(Integer id);
}
