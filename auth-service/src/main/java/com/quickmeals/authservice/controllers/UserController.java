package com.quickmeals.authservice.controllers;

import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.DispatcherDto;
import com.quickmeals.authservice.dtos.VendorDto;
import jakarta.ws.rs.Path;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth-service/user")
public interface UserController {
    @GetMapping("/vendor")
    ResponseEntity<List<VendorDto>> getAllVendors();

    @GetMapping("/customer")
    ResponseEntity<List<CustomerDto>> getAllCustomers();

    @GetMapping("/dispatcher")
    ResponseEntity<List<DispatcherDto>> getAllDispatchers();

    @GetMapping("/{businessName}")
    ResponseEntity<List<VendorDto>> getVendorByBusinessName(@PathVariable("businessName") String businessName );
    @GetMapping("/{dispatcherTag}")
    ResponseEntity<DispatcherDto> getDispatcherByDispatcherTag(@PathVariable("dispatcherTag") String dispatcherTag);

    @GetMapping("/{vendorId}")
    ResponseEntity<VendorDto> getVendorById(@PathVariable("vendorId") Integer vendorId);

    @GetMapping("/{customerId}")
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") Integer customerId);

    @GetMapping("/{dispatcherId}")
    ResponseEntity<DispatcherDto> getDispatcherById(@PathVariable("dispatcherId") Integer dispatcherId);

    @PutMapping("/{vendorId}")
    ResponseEntity<Boolean> updateVendorDetails(@PathVariable("vendorId") Integer vendorId, @RequestBody VendorDto vendorUpdate);

    @PutMapping("/{customerId}")
    ResponseEntity<Boolean> updateCustomerDetails(@PathVariable("customerId") Integer customerId, @RequestBody CustomerDto userUpdate);

    @PutMapping("/{dispatcherId}")
    ResponseEntity<Boolean> updateDispatcherDetails(@PathVariable("dispatcherId") Integer dispatcherId, @RequestBody DispatcherDto dispatcherDto);

    @DeleteMapping("/{vendorId}")
    ResponseEntity<Void> deleteVendor(@PathVariable("vendorId") Integer vendorId);

    @PutMapping("/{userId}")
    ResponseEntity<String> updateUserAccountStatus(@PathVariable("userId") Integer userId, @RequestParam("accountStatus") String accountStatus);

    @PutMapping("/{vendorId}/credit")
    ResponseEntity<Double> creditVendorWallet(@PathVariable("vendorId") Integer vendorId, @RequestParam Double amount);

    @PutMapping("/{vendorId}/debit")
    ResponseEntity<Double> debitVendorWallet(@PathVariable("vendorId") Integer vendorId, @RequestParam Double amount);

    @PutMapping("/{dispatcherId}/credit")
    ResponseEntity<Double> creditDispatcherWallet(@PathVariable("dispatcherId") Integer dispatcherId, @RequestParam Double amount);

    @PutMapping("/{dispatcherId}/debit")
    ResponseEntity<Double> debitDispatcherWallet(@PathVariable("dispatcherId") Integer dispatcherId, @RequestParam Double amount);

}
