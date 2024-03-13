package com.traderg.cli.services;

public class CommandTranslatorService {

    public void translateCommand(String command) {

        String words = command.toLowerCase();

        switch (words) {
            case "open inventory":
                openInventory();
                break;
            case "open marketplace":
                openMarketplace();
                break;
            case "open trade":
                openTrade();
                break;
            case "make trade":
                makeTrade();
                break;
            case "logout":
                logout();
                break;
            default:
                System.out.println("Invalid command: " + words);
        }

        
    }

    private void openInventory() {
        System.out.println("Inventory functionality...");
    }

    private void openMarketplace() {
        System.out.println("Marketplace functionality...");
    }
    
    private void openTrade() {
        System.out.println("trade open functionality...");
    }
    
    private void logout() {
        System.out.println("logout functionality...");
    }
    
    private void makeTrade() {
        System.out.println("make trade functionality...");
    }
}
