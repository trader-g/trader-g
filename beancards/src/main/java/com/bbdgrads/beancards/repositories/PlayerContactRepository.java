package com.bbdgrads.beancards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.entities.ContactType;
import com.bbdgrads.beancards.entities.Player;
import com.bbdgrads.beancards.entities.PlayerContact;

import java.util.Optional;

public interface PlayerContactRepository extends JpaRepository<PlayerContact, Long> {
    Optional<PlayerContact> findByPlayerAndContactType(Player player, ContactType contactType);
}
