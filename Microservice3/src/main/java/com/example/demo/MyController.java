package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	
	
	@GetMapping("/service3")
	public String callM3()
	{
		return "Response from Microservice 3";
	}

}
