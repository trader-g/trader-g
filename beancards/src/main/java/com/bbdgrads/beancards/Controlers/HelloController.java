package com.bbdgrads.beancards.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbdgrads.beancards.Services.DatabaseConnectionTestService;
import com.bbdgrads.beancards.Services.HelloService;

@RestController
public class HelloController {

	@Autowired
   	HelloService helloService;

	@Autowired
	DatabaseConnectionTestService databaseConnectionTestService;

	@GetMapping("/")
	public String index() {
		return helloService.getHelloMessage();
	}

	@GetMapping("/test")
	public String test() {
		return databaseConnectionTestService.testConnection() ? "Database connection successful" : "Database connection failed";
	}

}