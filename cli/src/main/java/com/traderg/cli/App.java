package com.traderg.cli;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.net.URI;
// move the logic from the backend that takes in the access token and put it in the cli and then let the cli pass an access token back to the backend
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        String auth_url = "https://github.com/login/oauth/authorize?client_id=d095913ff9b55112e726&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fplayer%2Fsignin&scope=user+user:email";
        if (args.length > 0 && args[0].equalsIgnoreCase("login")) {
            try {
                Desktop.getDesktop().browse(new URI(auth_url));
            } catch (Exception e) {
                System.out.println("Failed to open browser for login.");
            }
        }


    }
}