package com.bbdgrads.beancards.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OfferId")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "SellerId")
    private Player player;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<Give> gives;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<Receive> receives;

    @ManyToOne
    @JoinColumn(name = "StatusId")
    private Status status;

    protected Offer() {}

    public Offer(Player player, Status status) {
        this.player = player;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
            "Offer[id=%d, player='%s', gives='%s', receives='%s']",
            id, player, gives, receives);
    }

    public Integer getId() {
        return id;
    }

    public Integer getPlayerId() {
        return player.getPlayerId();
    }

    public Player getPlayer() {
        return player;
    } 

    public List<Give> getGives() {
        return gives;
    }
    
    public void setGives(List<Give> gives) {
        this.gives = gives;
    }

    public List<Receive> getReceives() {
        return receives;
    }

    public void setReceives(List<Receive> receives) {
        this.receives = receives;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
