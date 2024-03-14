package com.traderg.cli;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import com.traderg.cli.backend_models.PlayerWithToken;
import com.traderg.cli.services.BackendService;
import com.traderg.cli.services.BrowserService;
import com.traderg.cli.services.CommandTranslatorService;
import com.traderg.cli.services.EnvironmentsService;
import com.traderg.cli.services.HttpListenerService;
import com.traderg.cli.services.BackendService.HttpException;

public class App {
    private static EnvironmentsService environmentsService = new EnvironmentsService();
    private static BrowserService browserService = new BrowserService();
    private static HttpListenerService httpListenerService = new HttpListenerService(environmentsService);
    private static BackendService backendService = new BackendService(environmentsService);
    private static CommandTranslatorService commandTranslatorService = new CommandTranslatorService();

    private static Logger logger = Logger.getLogger(App.class.getName());

    private static void doSignIn() throws InterruptedException {
        try {
            String authUrl = String.format(
                    "https://github.com/login/oauth/authorize?client_id=%s&redirect_uri=%s/signin&scope=user",
                    environmentsService.githubClientId,
                    environmentsService.oauthCallbackHost);

            browserService.openBrowser(authUrl);
            String code = httpListenerService.startServerAndWaitForCode();

            final PlayerWithToken player = backendService.sendCodeToBackend(code);
            System.out.printf("Welcome %s\n", player.getDisplayName());
        } catch (IOException | HttpException e) {
            logger.severe("Could not sign in with Github. Please contact support.");
        }
    }

    private static void doShell() throws InterruptedException {
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

    private static void runCommand(String command) throws InterruptedException {
        
        if (command.equalsIgnoreCase("login")) {
            doSignIn();
        } else if (command.equalsIgnoreCase("shell")) {
            doShell();
        } else if (command.equalsIgnoreCase("help")) {
            doHelp();
        } else {
            commandTranslatorService.translateCommand(command);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length > 0) {
            runCommand(args[0]);
        } else {
            runCommand("shell");
        }
    }
}
