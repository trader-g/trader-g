package com.bbdgrads.beancards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.entities.Trade;

public interface TradeRepository extends JpaRepository<Trade, Integer> {

}
