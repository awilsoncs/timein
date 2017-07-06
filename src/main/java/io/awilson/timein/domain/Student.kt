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
    @ManyToOne
    var instructor: Instructor? = null
    @ManyToOne
    var course: Course? = null
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    var sessions: MutableList<Session> = ArrayList()
    @OneToOne
    var currentSession: Session? = null

    /**
     * Does the student have any active sessions?

     * @return true if the student has at least one active session.
     */
    fun anyActive(): Boolean? = sessions.stream().anyMatch { x -> x.isOpen }

    val fullName: String
        get() = "$firstName $lastName"
}
