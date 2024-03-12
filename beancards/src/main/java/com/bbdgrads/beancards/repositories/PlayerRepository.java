package com.bbdgrads.beancards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.entities.Player;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByDisplayName(String displayName);
}