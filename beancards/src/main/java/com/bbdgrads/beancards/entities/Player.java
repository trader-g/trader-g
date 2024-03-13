package com.bbdgrads.beancards.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlayerId")
    private Integer playerId;
    @Column(name = "DisplayName")
    private String displayName;

    @OneToMany(mappedBy = "player")
    private List<Trade> trades;
    @ManyToMany
    private List<Card> cards;

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

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
