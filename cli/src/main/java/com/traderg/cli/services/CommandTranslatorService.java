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
    private String previousCommand = "";

    public void translateCommand(String command) {

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
                        if(previousCommand.equals("make offer")){
                            makeExchangeOffer(wordArr);
                        }
                        else System.out.println("Invalid command. Can only create a potential exchange after the make offer command");
                    } 
                    else if (isInteger(wordArr[0])) {
                        if(previousCommand.equals("make trade")){
                            makeTradeHappen(Integer.parseInt(wordArr[0]));
                        }
                        else System.out.println("Invalid command. Can only use an id after the make trade command");
                        
                        makeTrade();
                    }
                    
                    else {
                        System.out.println("Invalid command:  " + words);
                    }

            }
            previousCommand = words;
        }
    }

    private void viewInventory() {
        // draft up http request
        // make http request to localhost:8080/inventory
        // receive response back from http
        // data is in body []
        // do what you will with it

        System.out.println("Inventory functionality...");

        // rH.testFunc();

        // HttpClient client = HttpClient.newHttpClient();
        // HttpRequest request = HttpRequest.newBuilder()
        //         .uri(URI.create("http://localhost:8080/inventory"))
        //         .header("Authorization", "Bearer " + authKey)
        //         .build();

        // HttpResponse<String> response;
        // try {
        //     response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //     System.out.println("Response from backend: " + response.body());
        //     int statusCode = response.statusCode();
        //     if (statusCode == 200) {
        //         System.out.println(response.body());

        //         // do something with this response body, lmao
        //     } else {
        //         throw new Exception("Failed to fetch inventory data. Response code: " + statusCode);
        //     }
        // } catch (Exception e) {
        //     System.out.println("Failed to send code to backend: " + e.getMessage());
        // }

    }

    private void viewLeaderboard() {
        System.out.println("Leaderboard functionality...");
    }

    private void viewOffers() {
        System.out.println("trade open functionality...");
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

    private boolean isInteger(String str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String fixMultipleSpaces(String cmd){
        return cmd.replaceAll("\\s+", " ");
    }

}
