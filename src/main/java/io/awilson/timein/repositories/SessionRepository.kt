package io.awilson.timein.repositories

import io.awilson.timein.domain.Session
import org.springframework.data.repository.PagingAndSortingRepository

interface SessionRepository : PagingAndSortingRepository<Session, Int>
