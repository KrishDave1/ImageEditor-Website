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
