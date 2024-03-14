package com.bbdgrads.beancards.services;

public interface JwtService {
    String generateToken(String username, Integer playerId);
    String extractDisplayName(String token);
    Boolean validateToken(String token);
    Integer extractPlayerId(String token);
}