package com.quickmeals.api.edgeserver;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
@Component
public class JwtService {
    private static final String SECRET_KEY = "gH5XCVEiEPCfkAJw7CFD6GvobuYHaagCmmoUf3AcOSVwTtCTJbEKxQiGx_7ZIf61a07gzEtIr51R_mhadXBzUeLV6xCj14eGgojPRv3nQ7xHMb822XRwYmsOtGwcWM-FKKXYq93_uVgT3HtDC0YOvV5ujv4GNZkgIb-QZe9fKnZ_xQua8vd3pt3CoQe4hdcYVPn2Qzlr86Lr9PdqS1hCY5KbYM2N7HXiVW2H_EO6KLcNBftmxmB2Klrp65TMucXo_6Eg8ApN8YJHJJsuh63Sw5tssc671VLSmf26aOFZE1RUMY9Ijv6r4V4DUJ-dRcq3wfegawRkRYGP7pyyL4tVKA";
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

    public boolean isTokenValid(String token) {
        return extractSingleClaim(token, Claims::getExpiration).before(new Date());
    }

}
