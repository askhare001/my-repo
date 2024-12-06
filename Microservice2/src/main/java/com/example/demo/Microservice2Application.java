package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Microservice2Application implements CommandLineRunner {
	
	@Value("${custom.message}")
	String message;

	public static void main(String[] args) {
		SpringApplication.run(Microservice2Application.class, args);
		System.out.println("App started");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(message);
		
	}

}
