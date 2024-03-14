package com.bbdgrads.beancards.services;

import com.bbdgrads.beancards.api_models.SignInResponse;

public interface AuthenticationService {
    String exchangeCodeForGithubToken(String code);
    SignInResponse signInWithGithubToken(String githubAccessToken);
}
