package com.bbdgrads.beancards.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbdgrads.beancards.dtos.CreateOfferDto;
import com.bbdgrads.beancards.dtos.LeaderboardDto;
import com.bbdgrads.beancards.dtos.TradeDto;
import com.bbdgrads.beancards.entities.Card;
import com.bbdgrads.beancards.entities.Offer;
import com.bbdgrads.beancards.services.MarketService;

@RestController
public class MarketControler {

	@Autowired
	MarketService marketService;

	@GetMapping("/cards")
	public List<Card> index() {
		return marketService.getCards();
	}

	@GetMapping("/offers")
	public List<Offer> offers() {
		return marketService.getOffers();
	}

	@GetMapping("player/offers")
	public ResponseEntity<List<Offer>> offers(@RequestParam Integer playerId) {
		List<Offer> offers =  marketService.getOffersByPlayerId(playerId);
		return ResponseEntity.ok(offers);
	}

	@PostMapping("/offer")
	public ResponseEntity<Offer> createOffer(@RequestBody CreateOfferDto createOfferDto) {
		Offer offer = marketService.createOffer(createOfferDto);
		return ResponseEntity.ok(offer);
	}

	@GetMapping("/offer/cancel")
	public ResponseEntity<Boolean> cancelOffer(@RequestParam Integer offerId) {
		Boolean success = marketService.cancelOffer(offerId);
		return ResponseEntity.ok(success);
	}

	@PostMapping("/trade")
	public ResponseEntity<TradeDto> createTrade(@RequestParam Integer playerId, @RequestParam Integer offerId) {
		TradeDto trade = marketService.createTrade(playerId, offerId);
		return ResponseEntity.ok(trade);
	}

	@GetMapping("/Leaderboard")
	public List<LeaderboardDto> getLeaderboard() {
		return marketService.getLeaderboard();
	}
}