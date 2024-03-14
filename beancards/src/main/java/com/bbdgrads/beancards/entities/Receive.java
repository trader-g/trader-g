package com.bbdgrads.beancards.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Receive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReceiveId")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CardId")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "OfferId")
    private Offer offer;

    @Column(name = "Quantity")
    private Integer quantity;

    protected Receive() {
    }

    public Receive(Offer offer, Card card, Integer quantity) {
        this.offer = offer;
        this.card = card;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format(
            "Received[id=%d, card='%s', quantity='%d']",
            id, card, quantity);
    }

    public Integer getId() {
        return id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
