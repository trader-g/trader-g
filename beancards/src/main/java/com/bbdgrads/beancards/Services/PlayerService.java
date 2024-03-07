package com.bbdgrads.beancards.Services;

import com.bbdgrads.beancards.Entities.Player;

public interface PlayerService {
    Player login();
    boolean logout();
}
