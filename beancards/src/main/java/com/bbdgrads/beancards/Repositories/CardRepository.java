package com.bbdgrads.beancards.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.Entities.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}