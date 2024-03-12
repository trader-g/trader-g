package com.bbdgrads.beancards.Controllers;

import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bbdgrads.beancards.ApiModels.SignInRequest;


import org.springframework.beans.factory.annotation.Autowired;

import com.bbdgrads.beancards.Services.PlayerService;

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

	
	@GetMapping("/show-inventory")
	public String getInventory(Long id) {
		return playerService.getInventory(id).toString();
	}
}