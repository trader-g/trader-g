package com.bbdgrads.beancards.api_models;

import com.bbdgrads.beancards.entities.Player;

public class SignInResponse {
    private Player player;
    private String token;

    public SignInResponse(Player player, String token) {
        this.player = player;
        this.token = token;
    }

    // Getters
    public Player getPlayer() {
        return player;
    }

    public String getToken() {
        return token;
    }

    // Setters
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
