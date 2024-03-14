package com.traderg.cli.services;

import java.io.IOException;
import com.traderg.cli.services.HttpRequestHandler;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CommandTranslatorService {

    public int playerID;
    public String authKey;
    HttpRequestHandler rH = new HttpRequestHandler();

    public void translateCommand(String command) {

        String words = command.toLowerCase();
        String[] wordArr = command.toLowerCase().split("\\s+");

        if (wordArr.length < 3) {
            switch (words) {
                case "open inventory":
                    openInventory();
                    break;
                case "open marketplace":
                    openMarketplace();
                    break;
                case "open trade":
                    openTrade();
                    break;
                case "open player-list":
                    openPlayerList();
                    break;
                case "logout":
                    logout();
                    break;
                default:
                    System.out.println("Invalid command:  " + words);
            }
        } else {
            switch (words) {
                case "make offer":
                    makeOffer(wordArr[2]);
                    break;
                case "make trade":
                    makeTrade(wordArr[2]);
                    break;
            }
        }

    }

    private void openInventory() {
        // draft up http request
        // make http request to localhost:8080/inventory
        // receive response back from http
        // data is in body []
        // do what you will with it

        System.out.println("Inventory functionality...");

        rH.testFunc();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/inventory"))
                .header("Authorization", "Bearer " + authKey)
                .build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response from backend: " + response.body());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                System.out.println(response.body());

                // do something with this response body, lmao
            } else {
                throw new Exception("Failed to fetch inventory data. Response code: " + statusCode);
            }
        } catch (Exception e) {
            System.out.println("Failed to send code to backend: " + e.getMessage());
        }

    }

    private void openMarketplace() {
        System.out.println("Marketplace functionality...");
    }

    private void openTrade() {
        System.out.println("trade open functionality...");
    }

    private void logout() {
        System.out.println("logout functionality...");
    }

    private void makeTrade(String tradeID) {
        System.out.println("make trade functionality...");
    }

    private void openPlayerList() {

    }

    private void makeOffer(String offerID) {
    }

}
