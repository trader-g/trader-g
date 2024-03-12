package com.bbdgrads.beancards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.entities.ContactType;

import java.util.Optional;

public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {
    Optional<ContactType> findByContactType(String email);
}
