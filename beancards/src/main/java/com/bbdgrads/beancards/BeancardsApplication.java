package com.bbdgrads.beancards;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.bbdgrads.beancards.Entities.Size;
import com.bbdgrads.beancards.Entities.Type;
import com.bbdgrads.beancards.Entities.Card;
import com.bbdgrads.beancards.Repositories.CardRepository;
import com.bbdgrads.beancards.Repositories.SizeRepository;
import com.bbdgrads.beancards.Repositories.TypeRepository;

@SpringBootApplication
public class BeancardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeancardsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
		SizeRepository sizeRepository,
		TypeRepository typeRepository,
		CardRepository cardRepository
		) 
	{
		return args -> {
			var sizes = sizeRepository.saveAll(Arrays.asList(
				new Size("Small"),
				new Size("Medium"),
				new Size("Large")
			));

			var types = typeRepository.saveAll(Arrays.asList(
				new Type("Type 1"),
				new Type("Type 2"),
				new Type("Type 3")
			));

			cardRepository.saveAll(Arrays.asList(
				new Card(sizes.get(0), types.get(0))
				new Card("Card 2", "Card 2 Description", 2),
				new Card("Card 3", "Card 3 Description", 3),
				new Card("Card 4", "Card 4 Description", 4),
				new Card("Card 5", "Card 5 Description", 5)
			));
			
		};
	}

}
