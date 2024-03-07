package com.bbdgrads.beancards.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.Entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

}