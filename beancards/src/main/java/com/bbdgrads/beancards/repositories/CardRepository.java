package com.bbdgrads.beancards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.entities.Card;

public interface CardRepository extends JpaRepository<Card, Integer> {

}