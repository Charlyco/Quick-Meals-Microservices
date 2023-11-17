package com.quickmeals.authservice.services;

import com.quickmeals.authservice.customtypes.AccountStatus;
import com.quickmeals.authservice.customtypes.Location;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.VendorDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<VendorDto> getAllVendors();
    List<VendorDto> getVendorByBusinessName(String businessName);
    VendorDto getVendorByUserId(Integer userId);
    Boolean updateVendorDetails(Integer userId, VendorDto vendorDto);
    Boolean deleteUser(Integer userId);
    AccountStatus updateUserAccountStatus(Integer userId, AccountStatus accountStatus);
    List<CustomerDto> getAllCustomers();
    Boolean updateVendorLocation(Integer userId, Location location);
    Location queryVendorLocation(Integer userId);
    CustomerDto getCustomerByUserId(Integer userId);

}
