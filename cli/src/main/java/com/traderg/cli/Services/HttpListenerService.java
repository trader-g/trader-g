package com.traderg.cli.services;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicReference;

public class HttpListenerService {
    private AtomicReference<String> authorizationCode = new AtomicReference<>("");

    public String startServerAndWaitForCode() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
            server.createContext("/signin", exchange -> {
                String query = exchange.getRequestURI().getQuery();
                String response = "You can close this window now.";
                if (query != null && query.startsWith("code=")) {
                    authorizationCode.set(query.substring(5));
                    exchange.sendResponseHeaders(200, response.getBytes().length);
                    exchange.getResponseBody().write(response.getBytes());
                } else {
                    exchange.sendResponseHeaders(400, 0);
                }
                exchange.close();

                // Stop server after handling the request
                server.stop(0);
            });
            server.start();

            System.out.println("Server started. Waiting for GitHub callback...");

            // Wait for the code to be set
            while (authorizationCode.get().isEmpty()) {
                Thread.sleep(100); // waiting for a code
            }

            return authorizationCode.get();
        } catch (Exception e) {
            System.out.println("Server failed: " + e.getMessage());
            return null;
        }
    }
}

