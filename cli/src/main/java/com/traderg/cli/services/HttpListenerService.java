package com.traderg.cli.services;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Optional;

public class HttpListenerService {
    final EnvironmentsService environmentsService;
    Optional<String> authorizationCode = Optional.empty();

    public HttpListenerService(EnvironmentsService environmentsService) {
        this.environmentsService = environmentsService;
    }

    public String startServerAndWaitForCode() throws IOException, InterruptedException {
        HttpServer server = HttpServer.create(new InetSocketAddress(environmentsService.oauthCallbackPort), 0);
        server.createContext("/signin", exchange -> {
            String query = exchange.getRequestURI().getQuery();
            String response = "You can close this window now.";

            if (query != null && query.startsWith("code=")) {
                authorizationCode = Optional.of(query.substring(5));
                exchange.sendResponseHeaders(200, response.getBytes().length);
                exchange.getResponseBody().write(response.getBytes());
            } else {
                exchange.sendResponseHeaders(400, 0);
            }

            exchange.close();
            server.stop(0);
        });

        server.start();

        // Wait for the code to be set
        while (authorizationCode.isEmpty()) {
            Thread.sleep(100);
        }

        return authorizationCode.get();
    }
}
