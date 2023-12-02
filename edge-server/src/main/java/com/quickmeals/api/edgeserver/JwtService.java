package com.quickmeals.api.edgeserver;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
@Component
public class JwtService {
    private static final String SECRET_KEY = "Y29tcG91bmRtaXhzYXZlZGhhdHNpbGVudGluY3JlYXNlZXhjaGFuZ2VjdXR0aW5ncm8=";
    public <T> T extractSingleClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY.trim());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token) {
        return !extractSingleClaim(token, Claims::getExpiration).before(new Date());
    }

}
