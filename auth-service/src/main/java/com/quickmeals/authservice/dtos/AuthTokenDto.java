package com.quickmeals.authservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthTokenDto {
    private Integer tokenId;
    private String token;
    private boolean expired;
    private boolean revoked;
    private Integer userId;
}
