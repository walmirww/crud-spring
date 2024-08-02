package com.loiane.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loiane.model.Course;
import com.loiane.repository.CourseRepository;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;


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
    
    
}
