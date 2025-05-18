package com.example.quizservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.quizservice.feign") 
public class QuizserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizserviceApplication.class, args);
	}

}
