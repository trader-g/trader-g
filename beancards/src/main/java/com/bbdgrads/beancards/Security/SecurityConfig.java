package com.bbdgrads.beancards.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz // Use authorizeHttpRequests()
                        .requestMatchers("/player/sigin").permitAll() // Use requestMatchers()
                        .anyRequest().authenticated()
                )
                .oauth2Client(); // Use oauth2Client()
        return http.build();
    }
}
