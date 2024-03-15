package com.traderg.cli.backend_models;

import java.util.List;

public class Offer {
    private Long id; // or use Integer, depending on your ID strategy
    private Long playerId;
    private List<TradeItem> gives;
    private List<TradeItem> receives;
    private OfferStatus status;

    // Constructor
    public Offer() {}

    public Offer(Long id, Long playerId, List<TradeItem> gives, List<TradeItem> receives, OfferStatus status) {
        this.id = id;
        this.playerId = playerId;
        this.gives = gives;
        this.receives = receives;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public List<TradeItem> getGives() {
        return gives;
    }

    public void setGives(List<TradeItem> gives) {
        this.gives = gives;
    }

    public List<TradeItem> getReceives() {
        return receives;
    }

    public void setReceives(List<TradeItem> receives) {
        this.receives = receives;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }
}
