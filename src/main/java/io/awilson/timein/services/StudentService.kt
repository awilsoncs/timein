package io.awilson.timein.services

import io.awilson.timein.domain.Student

interface StudentService {
    fun listAllStudents(): Iterable<Student>

    fun getStudentById(id: Int): Student?

    fun saveStudent(student: Student): Student

    fun deleteStudent(id: Int)

    fun login(id: Int)

    fun logout(id: Int)
}
