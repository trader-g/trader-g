package com.bbdgrads.beancards.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdgrads.beancards.Entities.Card;
import com.bbdgrads.beancards.Entities.Trade;
import com.bbdgrads.beancards.Repositories.CardRepository;
import com.bbdgrads.beancards.Repositories.TradeRepository;

@Service
public class MarketServiceImpl implements MarketService{

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Override
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Override
    public List<Trade> getMarketTrades() {
        return tradeRepository.findAll();
    }

    @Override
    public boolean createTrade(Trade trade) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Trade> getTradesByPlayerId(Long playerId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
