package com.traderg.cli;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("login")) {
            try {
                Desktop.getDesktop().browse(new URI("http://localhost:8080/oauth2/authorization/google"));
            } catch (Exception e) {
                System.out.println("Failed to open browser for login.");
            }
        }


    }
}