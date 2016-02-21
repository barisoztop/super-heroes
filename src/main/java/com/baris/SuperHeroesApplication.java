package com.baris;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SuperHeroesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperHeroesApplication.class, args);
	}
	
	@RequestMapping("/")
    public String helloGreeting() {
		
        return "Hello REST";
    }
}
