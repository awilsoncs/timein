package io.awilson.timein.repositories;

import io.awilson.timein.domain.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Integer> {}
