package io.awilson.timein.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
class Course {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0
    @Version
    var version: Int = 0
    var title: String = ""
    @OneToMany(mappedBy = "course")
    var students: MutableList<Student> = ArrayList()
}
