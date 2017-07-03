package io.awilson.timein.controllers;

import io.awilson.timein.domain.Course;
import io.awilson.timein.services.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Test the CourseController.
 */
public class CourseControllerTest {

    private CourseController courseController;

    @Before
    public void setUp() {
        courseController = new CourseController();
    }

    @Test
    public void setCourseService() throws Exception {

    }

    @Test
    public void newCourse() throws Exception {
        Model model = mock(Model.class);
        String val = courseController.newCourse(model);
        assertEquals("courseform", val);
    }

    @Test
    public void saveCourse() throws Exception {
        CourseService courseService = mock(CourseService.class);
        Course course = mock(Course.class);
        when(course.getId()).thenReturn(5);

        courseController.setCourseService(courseService);
        String val = courseController.saveCourse(course);
        verify(courseService, times(1)).saveCourse(course);
    }

    @Test
    public void saveCourseRedirect() throws Exception {
        CourseService courseService = mock(CourseService.class);
        Course course = mock(Course.class);
        when(course.getId()).thenReturn(5);

        courseController.setCourseService(courseService);
        String val = courseController.saveCourse(course);
        assertEquals("redirect:/course/5", val);
    }

    @Test
    public void showCourse() throws Exception {

    }

    @Test
    public void list() throws Exception {
    }

    @Test
    public void edit() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

}