package com.loiane.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.CoursePageDTO;
import com.loiane.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@Validated
@RequestMapping("/api/courses/")

public class CursoController {

    private final CourseService courseService;

    public CursoController( CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public CoursePageDTO list(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
        return courseService.list(page, pageSize);
    }

    /*@GetMapping
    public List<CourseDTO> list() {
        return courseService.list();
    } */  
    
    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable @NotNull @Positive long id) {
        return courseService.findById(id);
    }

    /*public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive long id) {
        return courseService.findById(id)
            .map(recordFound -> ResponseEntity.ok().body(recordFound))
            .orElse(ResponseEntity.notFound().build());
    } */

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @Valid CourseDTO course) {
        return courseService.create(course);        
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NotNull @Positive long id, 
                            @RequestBody @Valid @NotNull CourseDTO course) {
        return courseService.update(id, course);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull @Positive long id, @RequestBody @Valid Course course) {
        return courseService.update(id, course)
            .map(recordFound -> ResponseEntity.ok().body(recordFound))
            .orElse(ResponseEntity.notFound().build());
    } */

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseService.delete(id);
    }  

    /*@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive long id) {
        if (courseService.delete(id)){
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }     */
    
}
