package com.bbdgrads.beancards.Services;

import java.util.List;

import com.bbdgrads.beancards.Entities.Card;
import com.bbdgrads.beancards.Entities.Player;

public interface PlayerService {
    List<Player> getPlayers();
    List<Card> getInventory();
}
