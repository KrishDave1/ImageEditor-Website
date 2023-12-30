package com.iiitb.imageEffectApplication;

import com.iiitb.imageEffectApplication.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ImageEffectApplication {
	@Autowired
	 LogRepository logRepository;
	public static void main(String[] args) {
		SpringApplication.run(ImageEffectApplication.class, args);
	}
}

// This code represents a basic Spring Boot application with MongoDB support. It includes annotations to enable Spring Boot
// features, MongoDB repositories, and automatic dependency injection. The main method starts the Spring Boot application.
// The injected LogRepository suggests that the application may involve interacting with MongoDB using Spring Data
// repositories.