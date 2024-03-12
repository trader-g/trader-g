package com.bbdgrads.beancards.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdgrads.beancards.Entities.Card;
import com.bbdgrads.beancards.Entities.BeanCard;
import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Repositories.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player login() {
        Player player = new Player("Player 1");
        player.setLoggedIn(true);
        playerRepository.save(player);
        return player;
    }

    @Override
    public Player logout(Long id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player != null) {
            player.setLoggedIn(false);
            playerRepository.save(player);
            return player;
        }
        return null;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return new ArrayList<Player>(playerRepository.findAll());
    }

    @Override
    public List<Card> getInventory(Long id) {
        Player player = playerRepository.findById(id).orElse(null);

        return player.getCards();
    }

    
}