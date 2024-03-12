package com.bbdgrads.beancards.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbdgrads.beancards.Entities.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {

}