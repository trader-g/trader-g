package com.bbdgrads.beancards.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}