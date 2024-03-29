package com.quickmeals.authservice.services;

import com.quickmeals.authservice.dtos.*;
import com.quickmeals.authservice.entities.*;

public interface EntityDtoConverter {
    UserDto convertCustomerToUserDto(Customer customer);
    UserDto convertVendorToUserDto(Vendor vendor);
    UserDto convertDispatcerToUserDto(Dispatcher dispatcher);
    UserDto convertUserToUserDto(User user);
    CustomerDto convertCustomerToDto(Customer customer);
    Customer convertDtoToCustomer(CustomerDto customerDto);
    VendorDto convertVendorToDto(Vendor vendor);
    Vendor convertDtoToVendor(VendorDto vendorDto);
    Dispatcher convertDtoToDispatcher(DispatcherDto dispatcherDto);
    DispatcherDto convertDispatcherToDto(Dispatcher dispatcher);

}
