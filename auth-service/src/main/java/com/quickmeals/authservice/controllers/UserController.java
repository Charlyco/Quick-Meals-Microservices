package com.quickmeals.authservice.controllers;

import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.VendorDto;
import jakarta.ws.rs.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/va/user")
public interface UserController {
    @GetMapping("/vendor")
    ResponseEntity<List<VendorDto>> getAllVendors();

    @GetMapping("/customer")
    ResponseEntity<List<CustomerDto>> getAllCustomers();

    @GetMapping("/{businessName}")
    ResponseEntity<List<VendorDto>> getVendorByBusinessName(@PathVariable("businessName") String businessName );

    @GetMapping("/{vendorId}")
    ResponseEntity<VendorDto> getVendorById(@PathVariable("vendorId") Integer vendorId);

    @PutMapping("/{vendorId}")
    ResponseEntity<Void> updateVendorDetails(@PathVariable("vendorId") Integer vendorId, @RequestBody VendorDto vendorUpdate);

    @DeleteMapping("/{vendorId}")
    ResponseEntity<Void> deleteVendor(@PathVariable("vendorId") Integer vendorId);

    @GetMapping("/{customerId}")
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") Integer customerId);

    @PutMapping("/{customerId}")
    ResponseEntity<Void> updateUserDetails(@PathVariable("customerId") Integer customerId, @RequestBody CustomerDto userUpdate);

    @PutMapping("/signOut")
    ResponseEntity<Void> signOut(@RequestParam("authToken") String authToken);
    @PutMapping("/password")
    ResponseEntity<Boolean> resetPassword(@PathVariable("userName") String userName, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String newPassword);
}
