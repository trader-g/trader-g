package com.bbdgrads.beancards.Services;

import java.util.List;

import com.bbdgrads.beancards.Entities.Card;
import com.bbdgrads.beancards.Entities.Trade;

public interface MarketService {
    public List<Card> getCards();
    public List<Trade> getMarketTrades();
    public boolean createTrade(Trade trade);
    public List<Trade> getTradesByPlayerId(Long playerId);
}
