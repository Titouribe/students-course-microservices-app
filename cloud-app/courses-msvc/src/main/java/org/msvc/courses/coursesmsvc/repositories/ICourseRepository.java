package org.msvc.courses.coursesmsvc.repositories;

import org.msvc.courses.coursesmsvc.model.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Long> {
}
