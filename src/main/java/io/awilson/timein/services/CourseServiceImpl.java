package io.awilson.timein.services;

import io.awilson.timein.domain.Course;
import io.awilson.timein.repositories.CourseRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Setter(onMethod = @_(@Autowired))
    private CourseRepository courseRepository;

    @Override
    public Iterable<Course> listAllCourses() { return courseRepository.findAll(); }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findOne(id);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Integer id) {
        courseRepository.delete(id);
    }
}