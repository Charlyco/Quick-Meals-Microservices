package com.quickmeals.api.edgeserver;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthPredicateFactory extends AbstractGatewayFilterFactory<AuthPredicateFactory.Config> {

    private final RouteValidator routeValidator;
    private final JwtService jwtService;
    public AuthPredicateFactory(Config configClass, RouteValidator routeValidator, JwtService jwtService) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Authorization header not found");
                }
            }
            String authToken =  Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
            if (authToken != null && authToken.startsWith("Bearer ")) {
                authToken = authToken.substring(7);
            }
            //validate token
            if (jwtService.isTokenValid(authToken)) {
                return chain.filter(exchange);
            }
            throw new RuntimeException("Invalid authentication token");
        };
    }

    @Component
    public static class Config{ }
}
