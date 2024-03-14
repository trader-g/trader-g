package com.bbdgrads.beancards.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Trades")
public class Trade {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TradeId")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "BuyerId")
  private Player player;

  @ManyToOne
  @JoinColumn(name = "OfferId")
  private Offer offer;

  protected Trade() {
  }

  public Trade(Player player, Offer offer) {
    this.player = player;
    this.offer = offer;
  }

  @Override
  public String toString() {
    return String.format(
        "Trade[id=%d, Seller='%d', Buyer='%d', offer='%s']",
        id, player.getPlayerId(), offer.getPlayerId(), offer);
  }

  public Long getId() {
    return id;
  }

  public Player getSeller() {
    return offer.getPlayer();
  }

  public Player getBuyer() {
    return player;
  }
}
