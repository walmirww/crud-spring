package com.loiane.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.loiane.model.Lesson;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
        @JsonProperty("_id") Long id, 
        @NotNull @NotBlank @Length(min = 5, max = 100) String name, 
        @NotBlank @Length(max = 10) @Pattern(regexp = "front-end|back-end") String category,
        List<Lesson> lessons) {    
    }
