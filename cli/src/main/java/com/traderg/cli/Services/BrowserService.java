package com.traderg.cli.Services;

import java.awt.Desktop;
import java.net.URI;

public class BrowserService {
    public void openBrowser(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception e) {
            System.out.println("Failed to open browser for login: " + e.getMessage());
        }
    }
}
