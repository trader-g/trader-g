package com.bbdgrads.beancards.Controlers;


import com.bbdgrads.beancards.Entities.Card;
import com.bbdgrads.beancards.Entities.Trade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbdgrads.beancards.Services.MarketService;

@RestController
public class MarketControler {

	@Autowired
   	MarketService marketService;

	@GetMapping("/cards")
	public List<Card> index() {
		return marketService.getCards();
	}

    @GetMapping("/trades")
    public List<Trade> trades() {
        return marketService.getMarketTrades();
    }
}