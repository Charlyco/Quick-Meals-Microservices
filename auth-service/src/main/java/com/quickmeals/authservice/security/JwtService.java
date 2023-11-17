package com.quickmeals.authservice.security;

import com.quickmeals.authservice.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtService {
    private static final String SECRET_KEY = "gH5XCVEiEPCfkAJw7CFD6GvobuYHaagCmmoUf3AcOSVwTtCTJbEKxQiGx_7ZIf61a07gzEtIr51R_mhadXBzUeLV6xCj14eGgojPRv3nQ7xHMb822XRwYmsOtGwcWM-FKKXYq93_uVgT3HtDC0YOvV5ujv4GNZkgIb-QZe9fKnZ_xQua8vd3pt3CoQe4hdcYVPn2Qzlr86Lr9PdqS1hCY5KbYM2N7HXiVW2H_EO6KLcNBftmxmB2Klrp65TMucXo_6Eg8ApN8YJHJJsuh63Sw5tssc671VLSmf26aOFZE1RUMY9Ijv6r4V4DUJ-dRcq3wfegawRkRYGP7pyyL4tVKA";
    public String extractUserName(String token) {

        return extractSingleClaim(token, Claims::getSubject);
    }

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
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateToken(
            Map<String, Object> extraClaims,
            User user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(User user) {
        return generateToken(new HashMap<>(), user);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractSingleClaim(token, Claims::getExpiration).before(new Date());
    }

}
