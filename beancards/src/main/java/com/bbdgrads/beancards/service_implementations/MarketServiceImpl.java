package com.bbdgrads.beancards.service_implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdgrads.beancards.dtos.CardQuantityDto;
import com.bbdgrads.beancards.dtos.CreateOfferDto;
import com.bbdgrads.beancards.dtos.TradeDto;
import com.bbdgrads.beancards.dtos.UpdateCardsDto;
import com.bbdgrads.beancards.entities.Card;
import com.bbdgrads.beancards.entities.Give;
import com.bbdgrads.beancards.entities.Offer;
import com.bbdgrads.beancards.entities.Player;
import com.bbdgrads.beancards.entities.Receive;
import com.bbdgrads.beancards.entities.Status;
import com.bbdgrads.beancards.repositories.CardRepository;
import com.bbdgrads.beancards.repositories.OfferRepository;
import com.bbdgrads.beancards.repositories.PlayerRepository;
import com.bbdgrads.beancards.repositories.StatusRepository;
import com.bbdgrads.beancards.services.MarketService;
import com.bbdgrads.beancards.services.PlayerService;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private PlayerService playerService;

    @Override
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @Override
    public List<Offer> getOffers() {
        return offerRepository.findAll();
    }

    @Override
    public List<Offer> getOffersByPlayerId(Integer playerId) {
        if (!playerRepository.existsById(playerId)) {
            throw new IllegalArgumentException("PlayerId not found");
        }

        Player player = playerRepository.findById(playerId).get();
        return offerRepository.findAll().stream().filter(offer -> offer.getPlayerId() == player.getPlayerId()).toList();
    }

    @Override
    public Offer createOffer(CreateOfferDto createOfferDto) {
        if (!playerRepository.existsById(createOfferDto.playerId)) {
            throw new IllegalArgumentException("PlayerId not found");
        }

        Player player = playerRepository.findById(createOfferDto.playerId).get();
        Status status = statusRepository.findById(1).get();
        Offer offer = new Offer(player, status);

        ArrayList<Give> gives = new ArrayList<Give>();
        createOfferDto.gives.stream().forEach(offerCardDto -> {
            if (!cardRepository.existsById(offerCardDto.cardId)) {
                throw new IllegalArgumentException("CardId not found");
            }
            else {
                Card card = cardRepository.findById(offerCardDto.cardId).get();
                Give give = new Give(offer, card, offerCardDto.quantity);
                // Check if player has enough cards
                validateGive(player, give);
                gives.add(give);
            }
        });

        ArrayList<Receive> receives = new ArrayList<Receive>();
        createOfferDto.receives.stream().forEach(offerCardDto -> {
            if (!cardRepository.existsById(offerCardDto.cardId)) {
                throw new IllegalArgumentException("CardId not found");
            }
            else {
                Card card = cardRepository.findById(offerCardDto.cardId).get();
                if (offerCardDto.quantity > 5) {
                    throw new IllegalArgumentException("Quantity requested cannot be greater than 5");
                }
                Receive receive = new Receive(offer, card, offerCardDto.quantity);
                receives.add(receive);
            }
        });

        offer.setGives(gives);
        offer.setReceives(receives);
        return offerRepository.save(offer); 
    }

    private void validateGive(Player player, Give give) {
        if (give.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative or zero");
        }

        player.getInventories().stream().filter(
            inventory -> inventory.getCard().getId() == give.getCard().getId()
            ).forEach(inventory -> {
                if (inventory.getQuantity() < give.getQuantity()) {
                    throw new IllegalArgumentException(
                        "Player does not have enough " + give.getCard().toString() + " to give"
                    );
                }
            }
        );
    }

    @Override
    public Boolean cancelOffer(Integer offerId) {
        if (!offerRepository.existsById(offerId)) {
            throw new IllegalArgumentException("OfferId not found");
        }

        Offer offer = offerRepository.findById(offerId).get();
        Status status = statusRepository.findById(2).get();

        offer.setStatus(status);
        offerRepository.save(offer);

        return true;
    }

    @Override
    public TradeDto createTrade(Integer playerId, Integer offerId) {
        if (!offerRepository.existsById(offerId)) {
            throw new IllegalArgumentException("OfferId not found");
        }

        if (!playerRepository.existsById(playerId)) {
            throw new IllegalArgumentException("PlayerId not found");
        }

        Offer offer = offerRepository.findById(offerId).get();

        if (offer.getPlayerId() == playerId) {
            throw new IllegalArgumentException("Cannot trade with yourself");
        }

        if (offer.getStatus().getId() != 1) {
            throw new IllegalArgumentException("Offer is no longer available");
        }

        Player seller = playerRepository.findById(playerId).get();
        Player buyer = playerRepository.findById(offer.getPlayerId()).get();

        // handle trade receives
        List<CardQuantityDto> receives = offer.getReceives().stream().map(receive -> {
            // Check if buyer has needed cards
            validateReceive(buyer, receive);
            // Create cardQuantityDto
            CardQuantityDto cardQuantityDto = new CardQuantityDto();
            cardQuantityDto.cardId = receive.getCard().getId();
            cardQuantityDto.quantity = receive.getQuantity();
            return cardQuantityDto;
        }).toList();

        
        // handle trade gives
        List<CardQuantityDto> gives = offer.getGives().stream().map(give -> {
            // Create cardQuantityDto
            CardQuantityDto cardQuantityDto = new CardQuantityDto();
            cardQuantityDto.cardId = give.getCard().getId();
            cardQuantityDto.quantity = give.getQuantity();
            return cardQuantityDto;
        }).toList();
        
        // Create trade
        tradeCard(buyer, seller, gives);
        tradeCard(seller, buyer, receives);

        TradeDto tradeDto = new TradeDto();
        tradeDto.buyerId = buyer.getPlayerId();
        tradeDto.sellerId = seller.getPlayerId();
        tradeDto.receivedCards = receives;
        tradeDto.givenCards = gives;

        return tradeDto;
    }

    private void tradeCard(Player giver, Player receiver, List<CardQuantityDto> cardQuantityDtos){

        UpdateCardsDto updateCardsDto = new UpdateCardsDto();

        updateCardsDto.playerId = receiver.getPlayerId();
        updateCardsDto.cards = cardQuantityDtos;
        Player player1 = playerService.updateCards(updateCardsDto);
        playerRepository.save(player1);

        updateCardsDto.playerId = giver.getPlayerId();
        updateCardsDto.cards = cardQuantityDtos.stream().map(cardQuantityDto -> {
            cardQuantityDto.quantity = -cardQuantityDto.quantity;
            return cardQuantityDto;
        }).toList();
        Player player2 = playerService.updateCards(updateCardsDto);
        playerRepository.save(player2);
    }

    private void validateReceive(Player player, Receive receive) {
        if (receive.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative or zero");
        }

        player.getInventories().stream().filter(
            inventory -> inventory.getCard().getId() == receive.getCard().getId()
            ).forEach(inventory -> {
                if (inventory.getQuantity() < receive.getQuantity()) {
                    throw new IllegalArgumentException(
                        "Player does not have enough " + receive.getCard().toString() + " to make trade"
                    );
                }
            }
        );
    }


}
