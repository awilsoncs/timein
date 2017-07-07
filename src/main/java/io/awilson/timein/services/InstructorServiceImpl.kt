package io.awilson.timein.services

import io.awilson.timein.domain.Instructor
import io.awilson.timein.repositories.InstructorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InstructorServiceImpl : InstructorService {

    @Autowired
    lateinit var repo: InstructorRepository

    override fun listAllInstructors(): Iterable<Instructor> = repo.findAll()

    override fun getInstructorById(id: Int): Instructor = repo.findOne(id)
            ?: throw Exception("Instructor:$id not found")

    override fun saveInstructor(instructor: Instructor): Instructor = repo.save(instructor)

    override fun deleteInstructor(id: Int) = repo.delete(id)
}