package com.bbdgrads.beancards.services;

import java.util.List;

import com.bbdgrads.beancards.dtos.CreateOfferDto;
import com.bbdgrads.beancards.dtos.LeaderboardDto;
import com.bbdgrads.beancards.dtos.TradeDto;
import com.bbdgrads.beancards.entities.Card;
import com.bbdgrads.beancards.entities.Offer;

public interface MarketService {
    public List<Card> getCards();

    public List<Offer> getOffers();

    public Offer createOffer(CreateOfferDto createOfferDto);

    public Boolean cancelOffer(Integer offerId);

    public List<Offer> getOffersByPlayerId(Integer playerId);

    public TradeDto createTrade(Integer playerId, Integer offerId);

    public List<LeaderboardDto> getLeaderboard();

}
