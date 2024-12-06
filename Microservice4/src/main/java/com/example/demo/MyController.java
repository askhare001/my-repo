package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.MyConfiguration;

@RestController
public class MyController {
	@Autowired
	MyConfiguration myConfig;
	
	
	
	  @Autowired RestTemplate resttemp;
	 
	
	@GetMapping("/")
	public String getData()
	{
		return "Hello from  "+ myConfig.getName() +" call me on " + myConfig.getPhoneno();
	}
	
	
	  @GetMapping("/call-microservice3") public String callM3() { return
	  resttemp.getForObject("http://localhost:8806/service2", String.class); }
	 

}
