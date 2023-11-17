package com.quickmeals.authservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(AuthFilter jwtFilter, AuthenticationProvider authenticationProvider, JwtAuthEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtFilter = jwtFilter;
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/api/v1/auth")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(policy -> policy.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
