package com.bbdgrads.beancards.Services;

import java.util.List;

import com.bbdgrads.beancards.Entities.Card;
import com.bbdgrads.beancards.Entities.Player;

public interface PlayerService {
    Player login();
    Player logout(Long id);
    List<Player> getPlayers();
    List<Card> getInventory();
}
