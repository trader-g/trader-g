package com.bbdgrads.beancards.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlayerId")
    private Integer playerId;
    @Column(name = "DisplayName")
    private String displayName;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Offer> offers;

    protected Player() {
    }

    public Player(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return String.format(
                "Player[playerId=%d, displayName='%s']",
                playerId, displayName);
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
