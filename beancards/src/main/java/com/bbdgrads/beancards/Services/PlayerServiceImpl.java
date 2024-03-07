package com.bbdgrads.beancards.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Repositories.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player login() {
        return playerRepository.findById(1).orElseThrow();
    }

    @Override
    public boolean logout() {
        return true;
    }

    
}