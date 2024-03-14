package com.bbdgrads.beancards.services;

import com.bbdgrads.beancards.entities.Player;

public interface AuthenticationService {
    String exchangeCodeForGithubToken(String code);
    Player signInWithGithubToken(String githubAccessToken);
}
