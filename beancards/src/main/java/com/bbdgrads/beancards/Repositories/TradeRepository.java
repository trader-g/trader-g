package com.bbdgrads.beancards.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.Entities.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {

}