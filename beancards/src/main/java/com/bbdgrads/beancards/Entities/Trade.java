package com.bbdgrads.beancards.Entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "trades")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "player_id", referencedColumnName = "PlayerId")
    private Player player;

    @Column(name = "offer")
    @ManyToMany()
    @JoinTable(name = "trade_offer", 
      joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"))
    private List<Card> offer; 
    
    @Column(name = "receive")
    @ManyToMany()
    @JoinTable(name = "trade_receive", 
      joinColumns = @JoinColumn(name = "card_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "receive_id", referencedColumnName = "id"))
    private List<Card> receive;

    protected Trade() {}

    public Trade(Player player, List<Card> offer, List<Card> receive) {
        this.player = player;
        this.offer = offer;
        this.receive = receive;
    }

    @Override
    public String toString() {
        return String.format(
            "Trade[id=%d, player='%s', offer='%s', receive='%s']",
            id, player, offer, receive);
    }

    public Long getId() {
        return id;
    }
}
