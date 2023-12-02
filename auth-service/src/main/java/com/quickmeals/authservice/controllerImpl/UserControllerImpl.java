package com.quickmeals.authservice.controllerImpl;

import com.quickmeals.authservice.controllers.UserController;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.DispatcherDto;
import com.quickmeals.authservice.dtos.VendorDto;
import com.quickmeals.authservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    @Override
    public ResponseEntity<List<VendorDto>> getAllVendors() {
        return ResponseEntity.ok(userService.getAllVendors());
    }

    @Override
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(userService.getAllCustomers());
    }

    @Override
    public ResponseEntity<List<DispatcherDto>> getAllDispatchers() {
        return ResponseEntity.ok(userService.getAllDispatchers());
    }

    @Override
    public ResponseEntity<List<VendorDto>> getVendorByBusinessName(String businessName) {
        return ResponseEntity.ok(userService.getVendorByBusinessName(businessName));
    }

    @Override
    public ResponseEntity<DispatcherDto> getDispatcherByDispatcherTag(String dispatcherTag) {
        return ResponseEntity.ok(userService.getDispatcherByDispatcherTag(dispatcherTag));
    }

    @Override
    public ResponseEntity<VendorDto> getVendorById(Integer vendorId) {
        return ResponseEntity.ok(userService.getVendorByUserId(vendorId));
    }

    @Override
    public ResponseEntity<Boolean> updateVendorDetails(Integer vendorId, VendorDto vendorUpdate) {
        return ResponseEntity.ok(userService.updateVendorDetails(vendorId, vendorUpdate));
    }

    @Override
    public ResponseEntity<Boolean> updateCustomerDetails(Integer customerId, CustomerDto userUpdate) {
        return ResponseEntity.ok(userService.updateCustomerDetails(customerId, userUpdate));
    }

    @Override
    public ResponseEntity<String> updateUserAccountStatus(Integer userId, String accountStatus) {
        return ResponseEntity.ok(userService.updateUserAccountStatus(userId, accountStatus).toString());
    }

    @Override
    public ResponseEntity<Void> deleteVendor(Integer vendorId) {
        if (userService.deleteUser(vendorId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Double> creditVendorWallet(Integer vendorId, Double amount) {
        return ResponseEntity.ok(userService.creditVendorWallet(vendorId, amount));
    }

    @Override
    public ResponseEntity<Double> debitVendorWallet(Integer vendorId, Double amount) {
        return ResponseEntity.ok(userService.debitVendorWallet(vendorId, amount));
    }

    @Override
    public ResponseEntity<Double> creditDispatcherWallet(Integer dispatcherId, Double amount) {
        return ResponseEntity.ok(userService.creditDispatcherWallet(dispatcherId, amount));
    }

    @Override
    public ResponseEntity<Double> debitDispatcherWallet(Integer dispatcherId, Double amount) {
        return ResponseEntity.ok(userService.debitDispatcherWallet(dispatcherId, amount));
    }

    @Override
    public ResponseEntity<CustomerDto> getCustomerById(Integer customerId) {
        return ResponseEntity.ok(userService.getCustomerByUserId(customerId));
    }

    @Override
    public ResponseEntity<DispatcherDto> getDispatcherById(Integer dispatcherId) {
        return ResponseEntity.ok(userService.getDispatcherByUserId(dispatcherId));
    }

    @Override
    public ResponseEntity<Boolean> updateDispatcherDetails(Integer dispatcherId, DispatcherDto dispatcherDto) {
        return ResponseEntity.ok(userService.updateDispatcherDetails(dispatcherId, dispatcherDto));
    }

}
