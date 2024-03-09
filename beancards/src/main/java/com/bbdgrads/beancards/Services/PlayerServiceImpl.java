package com.bbdgrads.beancards.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Repositories.PlayerRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;


@Service
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String username = oAuth2User.getAttribute("name");

        Player player = new Player("Player");
        player.setLoggedIn(true);
        player.setUserName(username);
        playerRepository.save(player);
        return player;
    }

    @Override
    public boolean logout(Long id) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player != null) {
            player.setLoggedIn(false);
            playerRepository.save(player);
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<Player> getPlayers() {
        return new ArrayList<Player>(playerRepository.findAll());
    }

    
}