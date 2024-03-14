package com.bbdgrads.beancards.service_implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbdgrads.beancards.Dtos.CreateOfferDto;
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

        return player.getOffers();
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
        if (give.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
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


}
