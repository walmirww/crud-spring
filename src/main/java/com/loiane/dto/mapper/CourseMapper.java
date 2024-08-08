package com.loiane.dto.mapper;

import org.springframework.stereotype.Component;

import com.loiane.dto.CourseDTO;
import com.loiane.enums.Category;
import com.loiane.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO courseDTO) {
        //return new Course(courseDTO.id(), courseDTO.name(), courseDTO.category());
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "front-end" -> Category.FRONT_END;      
            case "back-end" -> Category.BACK_END;      
            default -> throw new IllegalArgumentException("Invalid value: " + value);          
        };
    }

}
