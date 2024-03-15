package com.traderg.cli.services;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.traderg.cli.backend_models.LeaderboardRecord;
import com.traderg.cli.services.BackendService.HttpException;
import com.traderg.cli.services.HttpRequestHandler;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class CommandTranslatorService {

    HttpRequestHandler rH = new HttpRequestHandler();
    private String previousCommand = "";

    private final BackendService backendService;
    
    public CommandTranslatorService(BackendService backendService){
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

    private void viewInventory() {
        // draft up http request
        // make http request to localhost:8080/inventory
        // receive response back from http
        // data is in body []
        // do what you will with it

        System.out.println("Inventory functionality...  ");

    }

    private void viewLeaderboard() throws HttpException, IOException, InterruptedException {
        backendService.getLeaderboard();
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
