package com.traderg.cli.services;

public class EnvironmentsService {
    private static final String PRODUCTION_HOST = "http://54.217.21.104";

    public final int oauthCallbackPort = 8888;
    public final String oauthCallbackHost = "http://localhost:" + oauthCallbackPort;
    public final String githubClientId = "d095913ff9b55112e726";
    public final String serverHost;

    public EnvironmentsService() {
        final String serverHostEnv = System.getenv("BEANCARDS_HOST");

        if (serverHostEnv == null) {
            serverHost = PRODUCTION_HOST;
        } else {
            serverHost = serverHostEnv;
        }
    }
}
