package com.bbdgrads.beancards.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

    protected Player() {}

    public Player(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return String.format(
            "Player[id=%d, userName='%s']",
            id, userName);
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = id;
    }

    public String getDisplayName() {
        return userName;
    }

    public void getDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
