package com.bbdgrads.beancards.services;

import java.util.List;

import com.bbdgrads.beancards.entities.Card;
import com.bbdgrads.beancards.entities.Trade;

public interface MarketService {
    public List<Card> getCards();

    public List<Trade> getMarketTrades();

    public boolean createTrade(Trade trade);

    public List<Trade> getTradesByPlayerId(Long playerId);
}
