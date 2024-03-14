package com.traderg.cli;

import java.util.Scanner;
import java.util.logging.Logger;

import com.traderg.cli.services.BackendService;
import com.traderg.cli.services.BrowserService;
import com.traderg.cli.services.HttpListenerService;

public class App {
    private static Logger logger = Logger.getLogger(App.class.getName());

    private static void doSignIn() {
        String authUrl = "https://github.com/login/oauth/authorize?client_id=d095913ff9b55112e726&redirect_uri=http://localhost:8888/signin&scope=user";

        BrowserService browserService = new BrowserService();
        browserService.openBrowser(authUrl);

        HttpListenerService httpListenerService = new HttpListenerService();
        String code = httpListenerService.startServerAndWaitForCode();

        if (code != null) {
            BackendService backendService = new BackendService();
            backendService.sendCodeToBackend(code);
        } else {
            logger.info("Failed to receive the authorization code.");
        }
    }

    private static void doShell() {
        Scanner keyboard = new Scanner(System.in);
        String command = "help";

        while (!command.equalsIgnoreCase("quit")) {
            runCommand(command);
            System.out.print("Enter command: ");
            command = keyboard.nextLine();
        }

        keyboard.close();
    }

    private static void doHelp() {
        System.out.println(
                "Welcome to to beancards. A platform where you can trade beancards with other players. Available commands are (login, help).");
    }

    private static void runCommand(String command) {
        if (command.equalsIgnoreCase("login")) {
            doSignIn();
        } else if (command.equalsIgnoreCase("shell")) {
            doShell();
        } else if (command.equalsIgnoreCase("help")) {
            doHelp();
        } else {
            throw new IllegalArgumentException("Unknown command " + command);
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            runCommand(args[0]);
        } else {
            runCommand("shell");
        }
    }
}
