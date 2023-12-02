package com.quickmeals.authservice.controllerImpl;

import com.quickmeals.authservice.controllers.AuthController;
import com.quickmeals.authservice.customtypes.AuthResponse;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.DispatcherDto;
import com.quickmeals.authservice.dtos.VendorDto;
import com.quickmeals.authservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;
    @Override
    public ResponseEntity<AuthResponse> createCustomer(CustomerDto customerDto) {
        AuthResponse authResponse = authService.createCustomer(customerDto);
        return ResponseEntity.ok(authResponse);
    }

    @Override
    public ResponseEntity<AuthResponse> createVendor(VendorDto vendorDto) {
        AuthResponse authResponse = authService.createVendor(vendorDto);
        return ResponseEntity.ok(authResponse);
    }

    @Override
    public ResponseEntity<AuthResponse> createDispatcher(DispatcherDto dispatcherDto) {
        AuthResponse authResponse = authService.createDispatcher(dispatcherDto);
        return ResponseEntity.ok(authResponse);
    }

    @Override
    public ResponseEntity<AuthResponse> signIn(String userName, String password) {
        AuthResponse authResponse = authService.signIn(userName, password);
        return ResponseEntity.ok(authResponse);
    }

    @Override
    public ResponseEntity<Void> logout(String token) {
        authService.signOut(token);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Boolean> resetPassword(String userName, String phone, String password) {
        Boolean isReset = authService.resetPassword(userName, phone, password);
        return ResponseEntity.ok(isReset);
    }
}
