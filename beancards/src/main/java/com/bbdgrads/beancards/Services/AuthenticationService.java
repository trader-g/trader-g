package com.bbdgrads.beancards.Services;

import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Repositories.PlayerRepository;
import com.bbdgrads.beancards.Repositories.PlayerContactRepository;

public interface AuthenticationService {
    String exchangeCodeForGithubToken(String code);
    Player signInWithGithubToken(String githubAccessToken);


}
