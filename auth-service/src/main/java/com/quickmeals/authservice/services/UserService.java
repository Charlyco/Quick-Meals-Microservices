package com.quickmeals.authservice.services;

import com.quickmeals.authservice.customtypes.AccountStatus;
import com.quickmeals.authservice.customtypes.Location;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.DispatcherDto;
import com.quickmeals.authservice.dtos.VendorDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface UserService {
    List<VendorDto> getAllVendors();
    List<CustomerDto> getAllCustomers();
    List<DispatcherDto> getAllDispatchers();
    List<VendorDto> getVendorByBusinessName(String businessName);
    DispatcherDto getDispatcherByDispatcherTag(String dispatcherTag);
    VendorDto getVendorByUserId(Integer userId);
    CustomerDto getCustomerByUserId(Integer userId);
    DispatcherDto getDispatcherByUserId(Integer userId);
    Boolean updateVendorDetails(Integer userId, VendorDto vendorDto);
    Boolean updateCustomerDetails(Integer userId, CustomerDto customerDto);
    Boolean  updateDispatcherDetails(Integer userId, DispatcherDto dispatcherDto);
    Boolean deleteUser(Integer userId);
    AccountStatus updateUserAccountStatus(Integer userId, String accountStatus);
    Double creditVendorWallet(Integer vendorId, Double amount);
    Double debitVendorWallet(Integer vendorId, Double amount);
    Double creditDispatcherWallet(Integer dispatcherId, Double amount);
    Double debitDispatcherWallet(Integer dispatcherId, Double amount);

}
