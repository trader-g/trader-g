package com.bbdgrads.beancards.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbdgrads.beancards.Services.PlayerService;

@RestController
public class PlayerController {

	@Autowired
   	PlayerService playerService;

	@GetMapping("/login")
	public String index() {
		return playerService.login().toString();
	}
}