package org.msvc.courses.coursesmsvc.model.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CourseDTO {

    private Long courseId;

    @NotBlank
    private String courseName;
}
