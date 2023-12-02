package com.quickmeals.authservice.controllers;

import com.quickmeals.authservice.customtypes.AuthResponse;
import com.quickmeals.authservice.dtos.DispatcherDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.VendorDto;

@RestController
@RequestMapping("api/v1/auth-service/auth")
public interface AuthController {
    @PostMapping("/customer")
    ResponseEntity<AuthResponse> createCustomer (@RequestBody CustomerDto customerDto);
    @PostMapping("/vendor")
    ResponseEntity<AuthResponse> createVendor (@RequestBody VendorDto vendorDto);
    @PostMapping("/dispatcher")
    ResponseEntity<AuthResponse> createDispatcher(@RequestBody DispatcherDto dispatcherDto);
    @GetMapping("/login")
    ResponseEntity<AuthResponse> signIn(@RequestParam("userName") String userName, @RequestParam("password") String password);
    @GetMapping("/logout")
    ResponseEntity<Void> logout(String token);
    @PostMapping("password-reset")
    ResponseEntity<Boolean> resetPassword(String userName, String phone, String password);
}
