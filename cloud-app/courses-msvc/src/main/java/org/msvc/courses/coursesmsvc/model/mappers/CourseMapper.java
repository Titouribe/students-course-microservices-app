package org.msvc.courses.coursesmsvc.model.mappers;

import org.mapstruct.Mapper;
import org.msvc.courses.coursesmsvc.model.dtos.CourseDTO;
import org.msvc.courses.coursesmsvc.model.entities.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO toDTO(Course course);

    Course toEntity(CourseDTO courseDTO);
}
