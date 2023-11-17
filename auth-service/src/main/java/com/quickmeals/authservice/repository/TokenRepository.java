package com.quickmeals.authservice.repository;

import com.quickmeals.authservice.entities.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.tokens.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<AuthToken, Integer> {
    Optional<AuthToken> findAuthTokenByToken(String token);
}
