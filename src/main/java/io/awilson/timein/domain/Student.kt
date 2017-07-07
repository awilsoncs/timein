package io.awilson.timein.domain

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    @ManyToOne
    var instructor: Instructor? = null
    @JsonIgnore
    @ManyToOne
    var course: Course? = null
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    var sessions: MutableList<Session> = ArrayList()
    @JsonIgnore
    @OneToOne
    var currentSession: Session? = null
    var active: Boolean = true

    /**
     * Does the student have any active sessions?

     * @return true if the student has at least one active session.
     */
    fun anyActive(): Boolean = sessions.stream().anyMatch { it.isOpen }

    val instructorId: Int?
        get() = instructor?.id

    val currentSessionId: Int?
        get() = currentSession?.id

    val courseId: Int?
        get() = course?.id

    val isOnline: Boolean
        get() = !(currentSession == null)

    val fullName: String
        get() = "$firstName $lastName"
}
