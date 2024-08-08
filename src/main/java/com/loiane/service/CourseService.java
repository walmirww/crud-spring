package com.loiane.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.loiane.exception.RecordNotFoundException;
import com.loiane.model.Course;
import com.loiane.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;        
    }

    public List<Course> list() {
        return courseRepository.findAll();
    } 

    public Course findById(@PathVariable @NotNull @Positive long id) {
        return courseRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(id));
    }

    /*public Optional<Course> findById(@PathVariable @NotNull @Positive long id) {
        return courseRepository.findById(id);
    } */

    public Course create(@Valid Course course) {
        return courseRepository.save(course);        
    }

    public Course update(@NotNull @Positive long id, @Valid Course course) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.getName());
                recordFound.setCategory(course.getCategory());
                return courseRepository.save(recordFound);               
            })
            .orElseThrow(()-> new RecordNotFoundException(id));  
    }     
    
    public void delete(@PathVariable @NotNull @Positive long id) {
        courseRepository.delete(courseRepository.findById(id)
            .orElseThrow(()-> new RecordNotFoundException(id)));
    }
        /*courseRepository.findById(id)
            .map(recordFound -> {
                courseRepository.deleteById(id);
                return true;
            })
            .orElseThrow(()-> new RecordNotFoundException(id)); */        
    
}
