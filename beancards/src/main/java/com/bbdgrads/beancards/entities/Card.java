package com.bbdgrads.beancards.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CardId")
    private Integer id;
    private String type;
    private String size;

    protected Card() {
    }

    public Card(String type, String size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format(
                "Card[id=%d, type='%s', size='%s']",
                id, type, size);
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
