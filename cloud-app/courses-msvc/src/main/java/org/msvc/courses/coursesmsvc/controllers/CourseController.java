package org.msvc.courses.coursesmsvc.controllers;

import org.msvc.courses.coursesmsvc.model.dtos.CourseDTO;
import org.msvc.courses.coursesmsvc.model.mappers.CourseMapper;
import org.msvc.courses.coursesmsvc.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseDTO> saveCourse(@RequestBody @Valid CourseDTO courseDTO){
        return new ResponseEntity<>(courseMapper.toDTO(courseService.saveCourse(courseMapper.toEntity(courseDTO))),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody @Valid CourseDTO courseDTO, @PathVariable Long id){
        return ResponseEntity.ok(courseMapper.toDTO(courseService.updateCourse(id, courseMapper.toEntity(courseDTO))));
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> findAll(){
        return ResponseEntity.ok(courseService.findAll().stream().map(courseMapper::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(courseMapper.toDTO(courseService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }
}
