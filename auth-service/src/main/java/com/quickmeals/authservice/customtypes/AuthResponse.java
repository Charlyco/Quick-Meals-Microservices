package com.quickmeals.authservice.customtypes;

import com.quickmeals.authservice.dtos.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    String jwtToken;
    UserDto userDto;
}
