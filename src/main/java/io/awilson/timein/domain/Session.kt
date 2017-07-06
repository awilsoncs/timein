package io.awilson.timein.domain

import javax.persistence.*
import java.time.*

import java.time.DayOfWeek.FRIDAY
import java.time.temporal.TemporalAdjusters.next

@Entity
class Session {
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
     * End the session now.
     */
    fun close() {
        timeEnd = Instant.now()
    }
}
