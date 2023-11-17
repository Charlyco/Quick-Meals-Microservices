package com.quickmeals.authservice.controllerImpl;

import com.quickmeals.authservice.controllers.AuthController;
import com.quickmeals.authservice.customtypes.AuthResponse;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.VendorDto;
import org.springframework.http.ResponseEntity;

public class AuthControllerImpl implements AuthController {
    @Override
    public ResponseEntity<AuthResponse> createCustomer(CustomerDto customerDto) {
        return null;
    }

    @Override
    public ResponseEntity<AuthResponse> createVendor(VendorDto vendorDto) {
        return null;
    }

    @Override
    public ResponseEntity<AuthResponse> signIn(String userName, String password) {
        return null;
    }

    @Override
    public ResponseEntity<Void> signOut(String authToken) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> resetPassword(String userName, String phoneNumber, String newPassword) {
        return null;
    }
}
