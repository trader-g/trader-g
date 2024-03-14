package com.traderg.cli.services;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class BackendService {
    public void sendCodeToBackend(String code) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/player"))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\"code\":\"" + code + "\"}"))
                .build();

        try {
            System.out.println("Sending code to backend..." + code);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response from backend: " + response.body());
        } catch (Exception e) {
            System.out.println("Failed to send code to backend: " + e.getMessage());
        }
    }
}

