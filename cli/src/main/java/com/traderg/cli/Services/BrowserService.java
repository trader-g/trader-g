package com.traderg.cli.Services;

import java.awt.Desktop;
import java.net.URI;
import java.util.logging.Logger;

public class BrowserService {
    final Logger logger = Logger.getLogger(BrowserService.class.getName());

    public void openBrowser(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (UnsatisfiedLinkError e) {
            logger.info("System does not have a web browser. Open the following link on your browser.");
            logger.info(url);
        } catch (Exception e) {
            logger.severe("Failed to open browser for login: " + e.getMessage());
        }
    }
}
