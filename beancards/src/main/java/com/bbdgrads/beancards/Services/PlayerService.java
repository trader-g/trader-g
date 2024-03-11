package com.bbdgrads.beancards.Services;

import java.util.List;

import com.bbdgrads.beancards.Entities.Player;

public interface PlayerService {
    /*Player login();
    String exchangeCodeForGithubToken(String code);*/
    List<Player> getPlayers();
}
