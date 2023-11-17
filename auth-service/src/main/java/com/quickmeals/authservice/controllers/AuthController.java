package com.quickmeals.authservice.controllers;

import com.quickmeals.authservice.customtypes.AuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.VendorDto;

@RestController
@RequestMapping("/api/v1/auth")
public interface AuthController {
    @PostMapping("/customer")
    ResponseEntity<AuthResponse> createCustomer (@RequestBody CustomerDto customerDto);
    @PostMapping("/vendor")
    ResponseEntity<AuthResponse> createVendor (@RequestBody VendorDto vendorDto);
    @GetMapping
    ResponseEntity<AuthResponse> signIn(@RequestParam("userName") String userName, @RequestParam("password") String password);
    @PutMapping("/signOut")
    ResponseEntity<Void> signOut(@RequestParam("authToken") String authToken);
    @PutMapping("/password")
    ResponseEntity<Boolean> resetPassword(@PathVariable("userName") String userName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String newPassword);
}
