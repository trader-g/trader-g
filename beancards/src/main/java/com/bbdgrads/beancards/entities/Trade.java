package com.bbdgrads.beancards.entities;

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
@Table(name = "Trades")
public class Trade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TradeId")
  private Long id;

  @ManyToOne()
  @JoinColumn(name = "PlayerId", referencedColumnName = "PlayerId")
  private Player player;

  @Column(name = "Offer")
  @ManyToMany()
  @JoinTable(
    name = "trade_offer", 
    joinColumns = @JoinColumn(name = "TradeId"), 
    inverseJoinColumns = @JoinColumn(name = "CardId")
  )
  private List<Card> offer;

  @Column(name = "receive")
  @ManyToMany()
  @JoinTable(
    name = "trade_offer", 
    joinColumns = @JoinColumn(name = "TradeId"), 
    inverseJoinColumns = @JoinColumn(name = "CardId")
  )
  private List<Card> receive;

  protected Trade() {
  }

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
