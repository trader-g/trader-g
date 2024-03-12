package com.bbdgrads.beancards.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.Entities.Player;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    //Optional<Player> findById(String githubId);

    Optional<Player> findByDisplayName(String displayName);
    //Optional<Player> findByEmail(String email);
}