package com.quickmeals.authservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Integer userId;
    private String userName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private Double latitude;
    private Double longitude;
    private String role;
    private String accountStatus;
}
