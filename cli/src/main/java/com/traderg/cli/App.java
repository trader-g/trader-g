package com.traderg.cli;

import com.traderg.cli.Services.BackendService;
import com.traderg.cli.Services.BrowserService;
import com.traderg.cli.Services.HttpListenerService;


public class App {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("login")) {
            String authUrl = "https://github.com/login/oauth/authorize?client_id=d095913ff9b55112e726&redirect_uri=http://localhost:8888/signin&scope=user";

            BrowserService browserService = new BrowserService();
            browserService.openBrowser(authUrl);

            HttpListenerService httpListenerService = new HttpListenerService();
            String code = httpListenerService.startServerAndWaitForCode();

            if (code != null) {
                BackendService backendService = new BackendService();
                backendService.sendCodeToBackend(code);
            } else {
                System.out.println("Failed to receive the authorization code.");
            }
        }
    }
}
