package com.bbdgrads.beancards;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bbdgrads.beancards.entities.Card;
import com.bbdgrads.beancards.entities.Player;
import com.bbdgrads.beancards.entities.Trade;
import com.bbdgrads.beancards.entities.Enums.Size;
import com.bbdgrads.beancards.entities.Enums.Type;
import com.bbdgrads.beancards.repositories.CardRepository;
import com.bbdgrads.beancards.repositories.PlayerRepository;
import com.bbdgrads.beancards.repositories.TradeRepository;

@SpringBootApplication
public class BeancardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeancardsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			CardRepository cardRepository,
			TradeRepository tradeRepository,
			PlayerRepository playerRepository) {
		return args -> {

			var cards = cardRepository.saveAll(Arrays.asList(
					new Card(Type.TYPE_A, Size.SMALL),
					new Card(Type.TYPE_B, Size.MEDIUM),
					new Card(Type.TYPE_C, Size.LARGE)));

			var players = playerRepository.saveAll(Arrays.asList(
					new Player("Player 1"),
					new Player("Player 2"),
					new Player("Player 3")));

			tradeRepository.saveAll(Arrays.asList(
					new Trade(
							players.get(0),
							cards,
							cards),
					new Trade(
							players.get(1),
							cards.subList(1, 2),
							cards)));
		};
	}

}
