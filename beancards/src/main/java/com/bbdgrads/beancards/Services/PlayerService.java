package com.bbdgrads.beancards.Services;

import java.util.ArrayList;

import com.bbdgrads.beancards.Entities.Player;

public interface PlayerService {
    Player login();
    boolean logout(Long id);
    ArrayList<Player> getPlayers();
}
