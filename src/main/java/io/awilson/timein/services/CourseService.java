package io.awilson.timein.services;

import io.awilson.timein.domain.Course;

public interface CourseService {
    Iterable<Course> listAllCourses();

    Course getCourseById(Integer id);

    Course saveCourse(Course course);

    void deleteCourse(Integer id);
}
