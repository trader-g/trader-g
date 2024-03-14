package com.traderg.cli.backend_models;

public class PlayerWithToken {
    private String playerId;
    private String displayName;

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getDisplayName() {
        return displayName;
    }
}
