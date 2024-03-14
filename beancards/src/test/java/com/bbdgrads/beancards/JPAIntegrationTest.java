package com.bbdgrads.beancards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbdgrads.beancards.entities.Player;
import com.bbdgrads.beancards.repositories.PlayerRepository;

@SpringBootTest
public class JPAIntegrationTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        Player player = new Player("Player 1");
        playerRepository.save(player);
        Player foundPlayer = playerRepository.findById(1).orElseThrow();
        assert(foundPlayer.getDisplayName().equals(player.getDisplayName()));
    }
}
