package io.awilson.timein.services

import io.awilson.timein.domain.Instructor
import io.awilson.timein.repositories.InstructorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InstructorServiceImpl : InstructorService {

    @Autowired
    lateinit var instructorRepository: InstructorRepository

    override fun listAllInstructors(): Iterable<Instructor> = instructorRepository.findAll()

    override fun getInstructorById(id: Int): Instructor = instructorRepository.findOne(id)

    override fun saveInstructor(instructor: Instructor): Instructor = instructorRepository.save(instructor)

    override fun deleteInstructor(id: Int) = instructorRepository.delete(id)
}