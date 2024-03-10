package com.traderg.cli;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.net.URI;
// move the logic from the backend that takes in the access token and put it in the cli and then let the cli pass an access token back to the backend
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("login")) {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/login/oauth/authorize"));
            } catch (Exception e) {
                System.out.println("Failed to open browser for login.");
            }
        }


    }
}