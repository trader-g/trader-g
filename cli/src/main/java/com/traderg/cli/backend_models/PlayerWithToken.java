package com.traderg.cli.backend_models;

public class PlayerWithToken {
    private int playerId;
    private String displayName;

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
