package com.bbdgrads.beancards.ServiceImplementations;

import java.util.ArrayList;
import java.util.List;

import com.bbdgrads.beancards.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdgrads.beancards.Entities.Card;
import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Repositories.PlayerRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;


@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public ArrayList<Player> getPlayers() {
        return new ArrayList<Player>(playerRepository.findAll());
    }

    @Override
    public List<Card> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }
}