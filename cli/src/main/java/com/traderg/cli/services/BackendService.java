package com.traderg.cli.services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.traderg.cli.backend_models.LeaderboardRecord;
import com.traderg.cli.backend_models.Offer;
import com.google.gson.JsonSyntaxException;
import com.traderg.cli.backend_models.InventoryItem;
import com.traderg.cli.backend_models.PlayerWithToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;

public class BackendService {
    final EnvironmentsService environmentsService;

    final HttpClient client = HttpClient.newHttpClient();

    final Gson gson = new Gson();

    Optional<PlayerWithToken> currentPlayer = Optional.empty();

    public BackendService(EnvironmentsService environmentsService) {
        this.environmentsService = environmentsService;
    }

    private HttpRequest.Builder startJsonRequest(String path) {
        if(currentPlayer.isEmpty() && !path.equals("/player")) {
            throw new IllegalStateException("Please log in before continuing.");
        } else {
            return HttpRequest.newBuilder().uri(
                URI.create(
                        String.format("%s%s?userId=%s", environmentsService.serverHost, path,
                                currentPlayer.map(PlayerWithToken::getPlayerId).orElse(null))))
                .header("Content-Type", "application/json");
        }

    }

    private String bodyAsString(HttpResponse<String> response) throws HttpException {
        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new HttpException(response.body());
        }
    }

    private String asJsonString(Map<String, Object> json) {
        return gson.toJson(json);
    }

    public Optional<PlayerWithToken> getCurrentPlayer() {
        return currentPlayer;
    }

    public PlayerWithToken sendCodeToBackend(String code) throws HttpException, IOException, InterruptedException {
        final Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("code", code);

        HttpRequest request = startJsonRequest("/player")
                .PUT(HttpRequest.BodyPublishers.ofString(asJsonString(requestBody)))
                .build();

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        currentPlayer = Optional.of(gson.fromJson(bodyAsString(response), PlayerWithToken.class));
        return currentPlayer.get();
    }

    public List<InventoryItem> getInventory()
            throws JsonSyntaxException, HttpException, IOException, InterruptedException {
        HttpRequest request = startJsonRequest(
                String.format("/players/%d/inventory", currentPlayer.map(PlayerWithToken::getPlayerId).orElse(null)))
                .GET()
                .build();

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        final Type tk = new TypeToken<List<Map<String, Object>>>() {
        }.getType();
        final List<Map<String, Object>> jsonList = gson.fromJson(bodyAsString(response), tk);

        return jsonList.stream().map(InventoryItem::fromJson).toList();
    }

    public static class HttpException extends Exception {
        HttpException(String message) {
            super(message);
        }
    }

    public List<LeaderboardRecord> getLeaderboard() throws HttpException, IOException, InterruptedException {
        HttpRequest request = startJsonRequest("/leaderboard")
                .GET()
                .build();

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Type tK = new TypeToken<List<LeaderboardRecord>>() {
        }.getType();
        return gson.fromJson(bodyAsString(response), tK);
    }

    public List<Offer> getOffers() throws IOException, InterruptedException {
        HttpRequest request = startJsonRequest("/offers")
        .GET()
        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Type listType = new TypeToken<List<Offer>>(){}.getType();
        return new Gson().fromJson(response.body(), listType);
    }
}
