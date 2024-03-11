package com.bbdgrads.beancards.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    protected Player() {}

    public Player(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return String.format(
            "Player[playerId=%d, displayName='%s']",
            playerId, displayName);
    }

    public Integer getId() {
        return playerId;
    }

    public void setId(Integer id) {
        this.playerId = playerId;
    }

    public String getDisplayName() {return displayName;}


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
// id
// username
// nned to check logged in state somehow, maybe a boolean

}
