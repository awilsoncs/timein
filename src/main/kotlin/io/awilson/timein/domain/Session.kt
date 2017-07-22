package io.awilson.timein.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import java.time.*

import java.time.DayOfWeek.FRIDAY
import java.time.temporal.TemporalAdjusters.next

@Entity
class Session {
    constructor() {}
    constructor(student: Student) {
        this.student = student
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    @Version
    var version: Int = 0
    var timeStart: Instant = Instant.now()
    var timeEnd: Instant? = null
    @ManyToOne
    var student: Student? = null

    /**
     * Return the length of this session.

     * @return
     */
    val duration: Duration
        get() = Duration.between(timeStart, timeEnd ?: Instant.now())

    /**
     * Get date that the session started on.

     * @return
     */
    val date: LocalDate
        @Transient
        get() = LocalDateTime.ofInstant(timeStart, ZoneId.systemDefault()).toLocalDate()

    /**
     * Return the date of the Friday following this Session.
     */
    val weekEnd: LocalDate
        @Transient
        get() = date.with(next(FRIDAY))

    /**
     * Return true if the session is currently open
     */
    val isOpen: Boolean
        @Transient
        get() = timeEnd == null

    /**
     * Return the id of the Student
     */
    val studentId: Int?
        get() = student?.id

    /**
     * End the session now.
     */
    fun close(): Session {
        timeEnd = Instant.now()
        return this
    }
}
