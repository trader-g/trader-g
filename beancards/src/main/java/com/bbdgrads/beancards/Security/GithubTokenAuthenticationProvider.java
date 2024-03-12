/*package com.bbdgrads.beancards.Security;

import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class GithubTokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();

        // Validate token with GitHub API (example)
        Player player = authenticationService.validateToken(token);

        if (player != null) {
            UserDetails user = User.withUsername(player.getDisplayName())
                    .password("") // No password needed for token auth
                    .authorities("ROLE_USER") // Assign roles as needed
                    .build();
            return new UsernamePasswordAuthenticationToken(user, token, user.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid token");
        }
    }

    @Override
    public boolean supports(Class authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}*/