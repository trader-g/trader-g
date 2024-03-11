package com.bbdgrads.beancards.Repositories;

import com.bbdgrads.beancards.Entities.ContactType;
import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Entities.PlayerContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerContactRepository extends JpaRepository<PlayerContact, Long>{
    Optional<PlayerContact> findByPlayerAndContactType(Player player, ContactType contactType);
}
