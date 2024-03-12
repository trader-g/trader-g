package com.bbdgrads.beancards.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @OneToOne
    @JoinColumn(name = "type_id")
    private Type type;
    @OneToOne
    @JoinColumn(name = "size_id")
    private Size size;

    protected Card() {}
    
    public Card(Type type, Size size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format(
            "Card[id=%d, type='%s', size='%s']",
            id, type, size);
    }

    public Long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public Size getSize() {
        return size;
    }
}
