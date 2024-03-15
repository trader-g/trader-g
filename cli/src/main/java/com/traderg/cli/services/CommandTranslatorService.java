package com.traderg.cli.services;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.traderg.cli.backend_models.InventoryItem;
import com.traderg.cli.backend_models.LeaderboardRecord;
import com.traderg.cli.backend_models.Offer;
import com.traderg.cli.backend_models.TradeItem;
import com.traderg.cli.services.BackendService.HttpException;
import com.traderg.cli.services.HttpRequestHandler;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

    private void viewOffers() {
         System.out.println("Fetching offers...");
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://54.217.21.104/offers"))
            .GET()
            .build();

    try {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            Type listType = new TypeToken<List<Offer>>(){}.getType();
            List<Offer> offers = new Gson().fromJson(response.body(), listType);

            for (Offer offer : offers) {
                System.out.println("Offer Details:");
                // Assuming direct access or appropriately named getters
                System.out.println("Player ID: " + offer.getPlayerId()); // Adjust based on actual method name
                
                printItems("Gives", offer.getGives()); // Adjust if needed
                printItems("Receives", offer.getReceives()); // Adjust if needed
                
                System.out.println("Status: " + offer.getStatus().getStatus()); // Adjust based on actual method name
                System.out.println("---");
            }
        } else {
            System.out.println("Failed to fetch offers. Response code: " + response.statusCode());
        }
    } catch (IOException | InterruptedException e) {
        System.out.println("An error occurred while fetching offers: " + e.getMessage());
    }
}

private void printItems(String label, List<TradeItem> items) {
    System.out.println(label + ":");
    for (TradeItem item : items) {
        // Assuming Card model has a toString that provides detailed info
        System.out.println(" - " + item.getCard() + ", Quantity: " + item.getQuantity()); // Adjust if needed
    }
}

    private void makeTrade() {
        System.out.println("make trade functionality...");
    }

    private void makeOffer() {
        System.out.println("make offer functionality...");
    }

    private void logout() {
        System.out.println("logout functionality...");
    }

    private void makeExchangeOffer(String[] potentialExchange) {
        System.out.println("makeExchangeOffer functionality...");
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

}
