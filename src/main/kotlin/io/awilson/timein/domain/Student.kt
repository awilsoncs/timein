package io.awilson.timein.domain

import javax.persistence.*

@Entity
class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    @Version
    var version: Int = 0
    var firstName: String = ""
    var lastName: String = ""
    @ManyToOne
    var instructor: Instructor? = null
    @ManyToOne
    var course: Course? = null
    @OneToMany(mappedBy = "student")
    var sessions: MutableList<Session> = ArrayList()
    var active: Boolean = true

    val isOnline: Boolean
        get() = sessions.any { it.isOpen }

    val fullName: String
        get() = "$firstName $lastName"

    /**
     * Log the student in and return the session.
     */
    fun login(): Session = if (isOnline) sessions.first { it.isOpen } else Session(this)

    /**
     * Log the student out and return the session if available.
     */
    fun logout(): Session? = if (isOnline) sessions.first { it.isOpen }.close() else null
}
