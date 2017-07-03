package io.awilson.timein.services

import io.awilson.timein.domain.Session
import io.awilson.timein.domain.Student
import io.awilson.timein.repositories.StudentRepository
import lombok.AllArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl : StudentService {

    @Autowired
    lateinit var studentRepository: StudentRepository

    @Autowired
    lateinit var sessionService: SessionService

    override fun listAllStudents(): Iterable<Student> {
        return studentRepository.findAll()
    }

    override fun getStudentById(id: Int): Student {
        return studentRepository.findOne(id)
    }

    override fun saveStudent(student: Student): Student {
        return studentRepository.save(student)
    }

    override fun deleteStudent(id: Int) {
        studentRepository.delete(id)
    }

    override fun login(id: Int) {
        val student = getStudentById(id)
        val session = Session()
        session.student = student
        sessionService.saveSession(session)

        student.currentSession = session
        studentRepository.save(student)
    }

    override fun logout(id: Int) {
        val student = getStudentById(id)
        val session = student.currentSession
        if (session == null) {
            return
        } else {
            session.close()
            sessionService.saveSession(session)
            student.currentSession = null
            studentRepository.save(student)
        }
    }
}
