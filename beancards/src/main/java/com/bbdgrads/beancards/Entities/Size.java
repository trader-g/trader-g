package com.bbdgrads.beancards.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "Size")
    private String size;

    protected Size() {}

    public Size(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format(
            "Size[type='%s']",
            size);
    }

    public Long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }
}
