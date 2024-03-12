package com.bbdgrads.beancards.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "Type")
    private String type;

    protected Type() {}

    public Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(
            "Type[type='%s']",
            type);
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
