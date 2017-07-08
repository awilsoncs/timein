package io.awilson.timein.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    @Version
    var version: Int = 0
    var firstName: String = ""
    var lastName: String = ""
    var email: String = ""
    @OneToMany(mappedBy = "instructor")
    var students: MutableList<Student> = ArrayList()

    val fullName: String
        get() = "$firstName $lastName"
}
