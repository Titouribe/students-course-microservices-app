package org.msvc.courses.coursesmsvc.services;

import org.msvc.courses.coursesmsvc.model.entities.Course;

import java.util.List;

public interface ICourseService {

    Course saveCourse(Course course);

    Course updateCourse(Long id, Course course);

    Course findById(Long id);

    List<Course> findAll();

    String  deleteCourse(Long id);
}
