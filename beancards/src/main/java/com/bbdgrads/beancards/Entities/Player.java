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
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String userName;
    @Column(name = "loggedin")
    private boolean loggedIn;

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

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
