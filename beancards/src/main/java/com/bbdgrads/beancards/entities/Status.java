package com.bbdgrads.beancards.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Status {

    @Id
    @Column(name = "StatusID")
    private Integer id;
  
    private String status;

    public Status() {
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
