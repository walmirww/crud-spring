package com.loiane.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.mapper.CourseMapper;
import com.loiane.exception.RecordNotFoundException;
import com.loiane.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;       
        this.courseMapper = courseMapper; 
    }

    public List<CourseDTO> list() {
        return courseRepository.findAll()
            .stream()
            .map(courseMapper::toDTO)
            .collect(Collectors.toList());        
    } 

    /*public List<CourseDTO> list() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTO> dtos = new ArrayList<>(courses.size());
        for (Course course : courses) {
            CourseDTO dto = new CourseDTO(course.getId(), course.getName(), course.getCategory());
            dtos.add(dto);
        }
        return dtos;
    }  */

    public CourseDTO findById(@NotNull @Positive long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(()-> new RecordNotFoundException(id));
    }

    /*public Optional<Course> findById(@PathVariable @NotNull @Positive long id) {
        return courseRepository.findById(id);
    } */

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));        
    }

    public CourseDTO update(@NotNull @Positive long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
            .map(recordFound -> {
                recordFound.setName(course.name());
                recordFound.setCategory(courseMapper.convertCategoryValue(course.category()));
                return courseMapper.toDTO(courseRepository.save(recordFound));               
            })
            .orElseThrow(()-> new RecordNotFoundException(id));  
    }     
    
    public void delete(@NotNull @Positive long id) {
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
