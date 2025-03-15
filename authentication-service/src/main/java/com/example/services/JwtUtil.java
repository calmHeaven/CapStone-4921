package com.example.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * Service class for handling JWT operations such as generating and parsing tokens.
 */
@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    private Key key;

    /**
     * Initializes the HMAC key using the secret provided in the application properties.
     */
    @PostConstruct
    public void initKey() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Parses the JWT token and retrieves the claims.4
     * Claims are the JSON data encoded in the JWT token. They contain information about the user, such as the user ID, role, and expiration date.
     *
     * @param token the JWT token
     * @return the claims contained in the token
     */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token).getBody();
    }

    /**
     * Retrieves the expiration date from the JWT token.
     * Claims are the <K,V> payload about the User and metadata about the token
     * @param token the JWT token
     * @return the expiration date of the token
     */
    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    /**
     * Generates a JWT token with the specified user ID, role, and token type.
     *
     * @param userId the user ID
     * @param role the user role
     * @param tokenType the type of the token (e.g., ACCESS)
     * @return the generated JWT token
     */
    public String generate(String userId, String role, String tokenType) {
        Map<String, String> claims = Map.of("id", userId, "role", role);
        long expMillis = "ACCESS".equalsIgnoreCase(tokenType)
                ? Long.parseLong(expiration) * 1000
                : Long.parseLong(expiration) * 1000 * 5;

        final Date now = new Date();
        final Date exp = new Date(now.getTime() + expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("id"))
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key)
                .compact();
    }

    /**
     * Checks if the JWT token is expired.
     *
     * @param token the JWT token
     * @return true if the token is expired, false otherwise
     */
    private boolean isExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }
}