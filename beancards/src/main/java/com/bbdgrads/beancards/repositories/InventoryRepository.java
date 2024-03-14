package com.bbdgrads.beancards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.entities.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

}