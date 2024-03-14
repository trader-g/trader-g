package com.bbdgrads.beancards.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import com.bbdgrads.beancards.service_implementations.JwtServiceImpl;


import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final JwtServiceImpl jwtService;

    public JwtAuthenticationTokenFilter(JwtServiceImpl jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
                System.out.println("This ran");
        final String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            System.out.println("Okay cool we got a jwt back and its: " + jwt);
            try {
                if (!jwtService.validateToken(jwt)) {
                    System.out.println("SHo it failed");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    System.out.println("OKAY");
                    return;
                }
            } catch (Exception e) {
                System.out.println("NOT OKAY");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
