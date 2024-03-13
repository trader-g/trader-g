package com.bbdgrads.beancards.service_implementations;

import java.util.ArrayList;
import java.util.List;

import com.bbdgrads.beancards.entities.Card;
import com.bbdgrads.beancards.entities.Player;
import com.bbdgrads.beancards.repositories.PlayerRepository;
import com.bbdgrads.beancards.services.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public List<Player> getPlayers() {
        return new ArrayList<>(playerRepository.findAll());
    }

    @Override
    public List<Card> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }
}