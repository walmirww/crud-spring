package com.loiane;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loiane.enums.Category;
import com.loiane.model.Course;
import com.loiane.model.Lesson;
import com.loiane.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDataBase( CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring");
			c.setCategory(Category.FRONT_END);
			
			Lesson l = new Lesson();
			l.setName("Introdução");
			l.setYoutubeUrl("01234567890");
			l.setCourse(c);
			c.getLessons().add(l);

			courseRepository.save(c);
			
		};
	}


}
