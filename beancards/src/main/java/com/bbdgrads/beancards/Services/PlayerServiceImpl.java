package com.bbdgrads.beancards.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdgrads.beancards.Entities.Card;
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
        String githubId = oAuth2User.getAttribute("id"); // Extract GitHub ID

        Player player = playerRepository.findByGithubId(githubId).orElse(null);
        if (player == null) {
            player = new Player(username);
            player.setGithubId(githubId);
        } else {
            // Update player information if needed
        }
        player.setLoggedIn(true);
        return playerRepository.save(player);
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
    public List<Card> getInventory() {
        // TODO Auto-generated method stub
        return null;
    }

    
}