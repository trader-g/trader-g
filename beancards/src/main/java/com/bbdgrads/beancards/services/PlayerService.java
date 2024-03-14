package com.bbdgrads.beancards.services;

import java.util.List;

import com.bbdgrads.beancards.entities.Card;
import com.bbdgrads.beancards.entities.Player;

public interface PlayerService {
    Player getPlayerById(Integer id);
    List<Player> getPlayers();

    List<Card> getInventory(int playerID);
    Player addPlayer(Player player);
}
