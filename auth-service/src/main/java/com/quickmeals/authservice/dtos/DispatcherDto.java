package com.quickmeals.authservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DispatcherDto {
    private Integer userId;
    private String userName;
    private String password;
    private String dispatcherTag;
    private String email;
    private String phoneNumber;
    private String address;
    private Double latitude;
    private Double longitude;
    private String role;
    private String accountStatus;
    private Double rating;
    private Integer dailyDeliveries;
    private Double bonuses;
    private Double walletBalance;
    private String bankName;
    private String accountName;
    private Long accountNumber;
}
