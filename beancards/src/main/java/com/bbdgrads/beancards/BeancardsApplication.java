package com.bbdgrads.beancards;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bbdgrads.beancards.entities.Player;
import com.bbdgrads.beancards.repositories.CardRepository;
import com.bbdgrads.beancards.services.PlayerService;

@SpringBootApplication
public class BeancardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeancardsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
		};
	}

}
