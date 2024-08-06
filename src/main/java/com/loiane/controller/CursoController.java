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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;





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
    
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable long id) {
        return courseRepository.findById(id)
            .map(recordFound -> ResponseEntity.ok().body(recordFound))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course) {
        return courseRepository.save(course);        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable long id, @RequestBody Course course) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.getName());
                recordFound.setCategory(course.getCategory());
                Course updated = courseRepository.save(recordFound);
                return ResponseEntity.ok().body(updated);
            })
            .orElse(ResponseEntity.notFound().build());
    }
    
    /*public ResponseEntity<Course> create(@RequestBody Course course) {
        //System.out.println(course.getName());
        //return courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(courseRepository.save(course));
    }*/
    
    
    
}
