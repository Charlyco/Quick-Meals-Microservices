package com.quickmeals.authservice.dtos;

import com.quickmeals.api.custom_types.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VendorDto {
    private Integer userId;
    private String userName;
    private String password;
    private String managerFullName;
    private String businessName;
    private String email;
    private String phoneNumber;
    private String address;
    private String businessDescription;
    private Double latitude;
    private Double longitude;
    private String role;
    private String accountStatus;
    private Double rating;
    private Integer totalSales;
    private Double walletBalance;
    private String bankName;
    private String accountName;
    private Long accountNumber;
    private List<Integer> mealIdList;
    private List<Integer> mealOrderIdList;
}
