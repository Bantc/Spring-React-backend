package com.bart.movies.service.security;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private final SecretKey key;
    private final long expirationMs;

    public JwtService(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.expiration-ms}") long expirationMs) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.expirationMs = expirationMs;
    }


    public String generateToken(Map<String, Object> extraClaims, String username) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .header()
                    .add("type", "JWT")
                    .and()
                .claims()
                    .add(extraClaims)
                    .subject(username)
                    .issuedAt(now)
                    .expiration(exp)
                    .and()
                .signWith(key)
                .compact();
    }

    public String generateToken(String username) {
        return generateToken(Map.of(), username);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        try {
            Claims claims = extractAllClaims(token);
            String subject = claims.getSubject();
            Date expiration = claims.getExpiration();
            return subject.equals(username) && expiration.after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public java.util.List<String> extractRoles(String token) {
        Object roles = extractAllClaims(token).get("roles");
        if (roles instanceof java.util.List<?> list) {
            return (java.util.List<String>) list;
        }
        return java.util.List.of();
    }
}
