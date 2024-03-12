package com.bbdgrads.beancards.Controllers;

import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bbdgrads.beancards.Services.PlayerService;

@RestController
public class PlayerController {

	@Autowired
   	PlayerService playerService;

	@Autowired
	private AuthenticationService authenticationService;

	@GetMapping("/player/signin")
	public Player signIn(@RequestParam String code) {
		String token = authenticationService.exchangeCodeForGithubToken(code);
		return authenticationService.signInWithGithubToken(token);
	}
}