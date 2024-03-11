package com.bbdgrads.beancards.Repositories;

import com.bbdgrads.beancards.Entities.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactTypeRepository extends JpaRepository<ContactType, Long>{
    Optional<ContactType> findByContactType(String email);
}
