package com.quickmeals.authservice.services;

import com.quickmeals.authservice.customtypes.AuthResponse;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.VendorDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse createCustomer(CustomerDto customerDto);
    AuthResponse createVendor(VendorDto vendorDto);
    AuthResponse signIn(String userName, String password);
    void signOut(String token);
    Boolean resetPassword(String userName, String phoneNumber, String newPassword);
}
