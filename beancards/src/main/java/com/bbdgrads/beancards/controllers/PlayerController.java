package com.bbdgrads.beancards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bbdgrads.beancards.api_models.SignInRequest;
import com.bbdgrads.beancards.dtos.UpdateCardsDto;
import com.bbdgrads.beancards.entities.Player;
import com.bbdgrads.beancards.services.AuthenticationService;
import com.bbdgrads.beancards.services.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	PlayerService playerService;

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("/mock")
	public void mockPlayers() {
		playerService.addPlayer(new Player("player1"));
		playerService.addPlayer(new Player("player2"));
		playerService.addPlayer(new Player("player3"));
	}

	@GetMapping("/players")
	public Iterable<Player> getPlayers() {
		return playerService.getPlayers();
	}

	@PutMapping("/player")
	public Player signIn(@RequestBody SignInRequest request) {
		String token = authenticationService.exchangeCodeForGithubToken(request.getCode());
		return authenticationService.signInWithGithubToken(token);
	}

	@PutMapping("/player/cards")
	public Player giveCards(@RequestBody UpdateCardsDto updateCardsDto) {
		System.out.println(updateCardsDto);
		return playerService.updateCards(updateCardsDto);
	}
	
	@PutMapping("/player/noAuth")
	public Player addPlayer(@RequestParam String name) {
		return playerService.addPlayer(new Player(name));
	}
}