package com.traderg.cli.backend_models;

import java.util.Map;

public class InventoryItem {
    private int id;
    private Card card;
    private Integer quantity;

    public InventoryItem(int id, Card card, int quantity) {
        this.id = id;
        this.card = card;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Card getCard() {
        return card;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static InventoryItem fromJson(Map<String, Object> json) {
        return new InventoryItem(Double.valueOf(json.get("id").toString()).intValue(),
                Card.fromJson((Map<String, Object>) json.get("card")), Double.valueOf(json.get("quantity").toString()).intValue());
    }
}
