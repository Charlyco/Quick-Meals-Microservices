package com.quickmeals.authservice.controllerImpl;

import com.quickmeals.authservice.controllers.UserController;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.VendorDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UserControllerImpl implements UserController {
    @Override
    public ResponseEntity<List<VendorDto>> getAllVendors() {
        return null;
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return null;
    }

    @Override
    public ResponseEntity<List<VendorDto>> getVendorByBusinessName(String businessName) {
        return null;
    }

    @Override
    public ResponseEntity<VendorDto> getVendorById(Integer vendorId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateVendorDetails(Integer vendorId, VendorDto vendorUpdate) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteVendor(Integer vendorId) {
        return null;
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomerById(Integer customerId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateUserDetails(Integer customerId, CustomerDto userUpdate) {
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
