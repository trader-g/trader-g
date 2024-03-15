package com.traderg.cli.services;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.traderg.cli.backend_models.CreateOffer;
import com.traderg.cli.backend_models.InventoryItem;
import com.traderg.cli.backend_models.LeaderboardRecord;
import com.traderg.cli.backend_models.OfferCard;
import com.traderg.cli.backend_models.Offer;
import com.traderg.cli.backend_models.TradeItem;
import com.traderg.cli.services.BackendService.HttpException;
import com.traderg.cli.services.HttpRequestHandler;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class CommandTranslatorService {

    HttpRequestHandler rH = new HttpRequestHandler();
    private String previousCommand = "";

    private final BackendService backendService;

    public CommandTranslatorService(BackendService backendService) {
        this.backendService = backendService;
    }

    public void translateCommand(String command) throws HttpException, IOException, InterruptedException {

        String words = fixMultipleSpaces(command.toLowerCase());
        String[] wordArr = command.toLowerCase().split("\\s+");

        if (!words.equals(previousCommand) && words.length() > 0) {
            switch (words) {
                case "view inventory":
                    viewInventory();
                    break;
                case "view leaderboard":
                    viewLeaderboard();
                    break;
                case "view offers":
                    viewOffers();
                    break;
                case "make offer":
                    makeOffer();
                    break;
                case "make trade":
                    makeTrade();
                    break;
                case "logout":
                    logout();
                    break;
                default:
                    if (wordArr[0].equals("exchange")) {
                        if (previousCommand.equals("make offer")) {
                            makeExchangeOffer(wordArr);
                        } else
                            System.out.println(
                                    "Invalid command. Can only create a potential exchange after the make offer command");
                    } else if (isInteger(wordArr[0])) {
                        if (previousCommand.equals("make trade")) {
                            makeTradeHappen(Integer.parseInt(wordArr[0]));
                        } else
                            System.out.println("Invalid command. Can only use an id after the make trade command");

                        makeTrade();
                    }

                    else {
                        System.out.println("Invalid command:  " + words);
                    }

            }
            previousCommand = words;
        }
    }

    private void viewInventory() throws JsonSyntaxException, HttpException, IOException, InterruptedException {
        final List<InventoryItem> inventoryItems = backendService.getInventory();
        IntStream.rangeClosed(0, inventoryItems.size() - 1).forEach(index -> {
            System.out.printf("%d. %s %s %d\n", index, inventoryItems.get(index).getCard().getType(),
                    inventoryItems.get(index).getCard().getSize(), inventoryItems.get(index).getQuantity());
        });
    }

    private void viewLeaderboard() throws HttpException, IOException, InterruptedException {
        backendService.getLeaderboard();
    }

    private void viewOffers() throws IOException, InterruptedException {
        final List<Offer> offers = backendService.getOffers();

        for (Offer offer : offers) {
            System.out.println("Offer Details:");
            System.out.println("Player ID: " + offer.getPlayerId());

            printItems("Gives", offer.getGives());
            printItems("Receives", offer.getReceives());

            System.out.println("Status: " + offer.getStatus().getStatus());
            System.out.println("--------------");
        }
    }

    private void printItems(String label, List<TradeItem> items) {
        System.out.println(label + ":");
        for (TradeItem item : items) {
            System.out.println(" - " + item.getCard() + ", Quantity: " + item.getQuantity());
        }
    }

    private void makeTrade() throws JsonSyntaxException, HttpException, IOException, InterruptedException {
        final List<InventoryItem> inventoryItems = backendService.getInventory();
        IntStream.rangeClosed(0, inventoryItems.size() - 1).forEach(index -> {
            System.out.printf("%d. %s %s\n", index, inventoryItems.get(index).getCard().getType(),
                    inventoryItems.get(index).getCard().getSize());
        });
    }

    private void makeOffer() {
        System.out.println("make offer functionality...");
    }

    private void logout() {
        System.out.println("logout functionality...");
    }

    private void makeExchangeOffer(String[] potentialExchange) throws HttpException, IOException, InterruptedException {
        // "exchange <>x<>,<>x<> for <>x<>,<>x<>"
        // potentialExchange is split on spaces
        // "exchange", "<>x<>,<>x<>", "for", "<>x<>,<>x<>"
        String giveString = "";
        String receiveString = "";

        String[] giveCardArr;
        String[] receiveCardArr;
        if (potentialExchange[0].equals("exchange") && potentialExchange[2].equals("for")) {
            giveString = potentialExchange[1];
            receiveString = potentialExchange[3];

            int numCardsInGive = countNumCharsInString('x', giveString);
            int numCardsInReceive = countNumCharsInString('x', receiveString);

            if (numCardsInGive > 1) {
                giveCardArr = giveString.split(",");
            } else {
                giveCardArr = new String[] { giveString };
            }

            if (numCardsInReceive > 1) {
                receiveCardArr = receiveString.split(",");
            } else {
                receiveCardArr = new String[] { receiveString };
            }

            String[] cardSplitUp;
            int giveQuantity, giveCardId;
            List<OfferCard> giveOfferCards = new ArrayList<OfferCard>();
            List<OfferCard> receiveOfferCards = new ArrayList<OfferCard>();
            OfferCard thisOne = new OfferCard();
            thisOne.cardId = 10;
            thisOne.quantity = 300;
            for (String card : giveCardArr) {
                cardSplitUp = card.split("x");
                giveQuantity = Integer.parseInt(cardSplitUp[0]);
                giveCardId = Integer.parseInt(cardSplitUp[1]);

                thisOne.cardId = giveCardId;
                thisOne.quantity = giveQuantity;
                giveOfferCards.add(thisOne);
            }

            int receiveQuantity, receiveCardId;
            for (String card : receiveCardArr) {
                cardSplitUp = card.split("x");
                receiveQuantity = Integer.parseInt(cardSplitUp[0]);
                receiveCardId = Integer.parseInt(cardSplitUp[1]);

                thisOne.cardId = receiveCardId;
                thisOne.quantity = receiveQuantity;
                receiveOfferCards.add(thisOne);
            }

            CreateOffer offer = new CreateOffer();
            offer.playerId = 1;
            // giveOfferCards has give,
            offer.gives = giveOfferCards;
            offer.receives = receiveOfferCards;

            backendService.makeOffer(offer);
            System.out.println("Offer created");
        } else {
            System.out.println("Sorry, incorrect offer command!");
        }
    }

    private void makeTradeHappen(int tradeID) {
        System.out.println("makeTradeHappen functionality...");
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String fixMultipleSpaces(String str) {
        return str.replaceAll("\\s+", " ");
    }

    private int countNumCharsInString(char choice, String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == choice) {
                count++;
            }
        }
        return count;
    }

}
