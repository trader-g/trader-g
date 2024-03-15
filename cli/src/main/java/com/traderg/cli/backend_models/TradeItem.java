package com.traderg.cli.backend_models;

public class TradeItem {
    private Card card;
    private int quantity;

    // Constructor
    public TradeItem() {}

    public TradeItem(Card card, int quantity) {
        this.card = card;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
