package io.awilson.timein.services

import io.awilson.timein.domain.Instructor

interface InstructorService {
    fun listAllInstructors(): Iterable<Instructor>

    fun getInstructorById(id: Int): Instructor?

    fun saveInstructor(instructor: Instructor): Instructor

    fun deleteInstructor(id: Int)
}
