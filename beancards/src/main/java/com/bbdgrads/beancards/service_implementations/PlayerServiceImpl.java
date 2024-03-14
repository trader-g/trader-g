package com.bbdgrads.beancards.service_implementations;

import java.util.ArrayList;
import java.util.List;

import com.bbdgrads.beancards.dtos.UpdateCardsDto;
import com.bbdgrads.beancards.entities.Inventory;
import com.bbdgrads.beancards.entities.Player;
import com.bbdgrads.beancards.repositories.CardRepository;
import com.bbdgrads.beancards.repositories.PlayerRepository;
import com.bbdgrads.beancards.services.PlayerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player addPlayer(Player player) {
        ArrayList<Inventory> inventories = new ArrayList<Inventory>();
        cardRepository.findAll().forEach(card -> {
            Inventory inventory = new Inventory(player, card);
            inventories.add(inventory);
        });
        
        player.setInventories(inventories);
        playerRepository.save(player);
        return player;
    }

    @Override
    public Player getPlayerById(Integer id) {
        return playerRepository.findById(id).get();
    }

    @Override
    public Player updateCards(UpdateCardsDto updateCardsDto) {
        if (!playerRepository.existsById(updateCardsDto.playerId)) {
            throw new IllegalArgumentException("PlayerId not found");
        }

        Player player = playerRepository.findById(updateCardsDto.playerId).get();

        updateCardsDto.cards.stream().forEach(card -> {
            if (!cardRepository.existsById(card.cardId)) {
                throw new IllegalArgumentException("CardId not found");
            }
            else {
                Inventory inventory = player.getInventories().stream()
                    .filter(i -> i.getCard().getId() == card.cardId).findFirst().get();

                inventory.setQuantity(inventory.getQuantity() + card.quantity);
            }
        });

        return playerRepository.save(player);
    }
}