package com.bbdgrads.beancards.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InventoryId")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "PlayerId")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "CardId")
    private Card card;

    @Column(name = "Quantity")
    private Integer quantity;

    protected Inventory() {
    }

    public Inventory(Player player, Card card) {
        this.player = player;
        this.card = card;
        this.quantity = 0;
    }

    public Inventory(Player player, Card card, Integer quantity) {
        this.player = player;
        this.card = card;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format(
                "Inventory[id=%d, player='%s', card='%s', qiuntity='%d']",
                id, player, card, quantity);
    }

    public Integer getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public Card getCard() {
        return card;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
