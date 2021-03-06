package io.awilson.timein.domain

import javax.persistence.*
import java.time.Duration

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
    @OneToOne
    var currentSession: Session? = null
    var active: Boolean = true

    /**
     * Does the student have any active sessions?

     * @return true if the student has at least one active session.
     */
    fun isOnline(): Boolean? = sessions.stream().anyMatch { it.isOpen }

    val fullName: String
        get() = "$firstName $lastName"
}
