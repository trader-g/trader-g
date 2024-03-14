package com.bbdgrads.beancards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.entities.Offer;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

}
