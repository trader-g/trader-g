package com.bbdgrads.beancards.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbdgrads.beancards.Services.MarketService;
import com.bbdgrads.beancards.Services.PlayerService;

@RestController
public class MarketControler {

	@Autowired
   	MarketService marketService;

	@GetMapping("/cards")
	public String index() {
		return marketService.getCards();
	}
}