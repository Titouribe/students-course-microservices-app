package org.msvc.courses.coursesmsvc.services.impl;

import org.msvc.courses.coursesmsvc.constants.Constants;
import org.msvc.courses.coursesmsvc.constants.ErrorsConstants;
import org.msvc.courses.coursesmsvc.exceptions.RequestException;
import org.msvc.courses.coursesmsvc.model.entities.Course;
import org.msvc.courses.coursesmsvc.repositories.ICourseRepository;
import org.msvc.courses.coursesmsvc.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    @Transactional
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public Course updateCourse(Long id, Course course) {
        Course currentCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RequestException(ErrorsConstants.NOT_FOUND,
                        ErrorsConstants.notFound(Constants.COURSE, id.toString())));
        currentCourse.setCourseName(course.getCourseName());
        return courseRepository.save(currentCourse);
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RequestException(ErrorsConstants.NOT_FOUND,
                        ErrorsConstants.notFound(Constants.COURSE, id.toString())));
    }

    @Override
    public List<Course> findAll() {
        if (courseRepository.findAll().isEmpty())
            throw new RequestException(ErrorsConstants.NOT_FOUND, ErrorsConstants.EMPTY_LIST);
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public String deleteCourse(Long id) {
        Course currentCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RequestException(ErrorsConstants.NOT_FOUND,
                        ErrorsConstants.notFound(Constants.COURSE, id.toString())));
        courseRepository.deleteById(currentCourse.getCourseId());
        return Constants.foundAndDeleted(Constants.COURSE, id.toString());
    }
}
