package com.bbdgrads.beancards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Repositories.PlayerRepository;

@SpringBootTest
public class JPAIntegrationTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
        Player player = new Player("Player 1");
        playerRepository.save(player);
        Player foundPlayer = playerRepository.findById(1L).orElseThrow();
        assert(foundPlayer.getUserName().equals(player.getUserName()));
    }
}
