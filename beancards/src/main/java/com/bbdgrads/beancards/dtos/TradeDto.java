package com.bbdgrads.beancards.dtos;

import java.util.List;

public class TradeDto {
    public Integer buyerId;
    public Integer sellerId;
    public List<CardQuantityDto> receivedCards;
    public List<CardQuantityDto> givenCards;

}
