package com.loiane.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loiane.model.Course;
import com.loiane.repository.CourseRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/courses/")
@AllArgsConstructor
public class CursoController {

    private final CourseRepository courseRepository;

    //@RequestMapping( method=RequestMethod.GET)
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }       

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course) {
        return courseRepository.save(course);        
    }
    /*public ResponseEntity<Course> create(@RequestBody Course course) {
        //System.out.println(course.getName());
        //return courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(courseRepository.save(course));
    }*/
    
    
    
}
