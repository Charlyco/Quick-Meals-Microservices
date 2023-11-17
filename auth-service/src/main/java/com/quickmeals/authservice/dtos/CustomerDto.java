package com.quickmeals.authservice.dtos;

import com.quickmeals.api.custom_types.Role;
import com.quickmeals.api.entities.MealOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDto {
    private Integer userId;
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private Double latitude;
    private Double longitude;
    private String role;
    private String accountStatus;
    private List<Integer> previousOrderIdList;
    private List<Integer> favouriteFoodIdList;
    private List<Integer> favouriteVendorIdList;
}
