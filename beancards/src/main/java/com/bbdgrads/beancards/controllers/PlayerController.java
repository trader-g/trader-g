package com.bbdgrads.beancards.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bbdgrads.beancards.api_models.SignInRequest;
import com.bbdgrads.beancards.entities.Player;
import com.bbdgrads.beancards.services.AuthenticationService;
import com.bbdgrads.beancards.services.PlayerService;

@RestController
public class PlayerController {

	@Autowired
	PlayerService playerService;

	@Autowired
	private AuthenticationService authenticationService;

	@PutMapping("/player")
	public Player signIn(@RequestBody SignInRequest request) {
		String token = authenticationService.exchangeCodeForGithubToken(request.getCode());
		return authenticationService.signInWithGithubToken(token);
	}

	
	@GetMapping("/inventory")
	public String getInventory(@RequestParam int playerID) {
		return playerService.getInventory(playerID).toString();
	}
}