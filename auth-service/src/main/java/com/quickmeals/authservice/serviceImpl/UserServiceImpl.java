package com.quickmeals.authservice.serviceImpl;

import com.quickmeals.authservice.customtypes.AccountStatus;
import com.quickmeals.authservice.customtypes.Location;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.VendorDto;
import com.quickmeals.authservice.entities.User;
import com.quickmeals.authservice.entities.Vendor;
import com.quickmeals.authservice.repository.CustomerRepository;
import com.quickmeals.authservice.repository.UserRepository;
import com.quickmeals.authservice.repository.VendorRepository;
import com.quickmeals.authservice.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final EntityDtoConverter entityDtoConverter;

    public UserServiceImpl(CustomerRepository customerRepository, VendorRepository vendorRepository, UserRepository userRepository, EntityDtoConverter entityDtoConverter) {
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        this.userRepository = userRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    public List<VendorDto> getAllVendors() {
        List<VendorDto> vendorDtoList = new ArrayList<>();
        vendorRepository.findAll().forEach(vendor ->
                vendorDtoList.add(entityDtoConverter.convertVendorToDto(vendor)));
        return vendorDtoList;
    }

    @Override
    public List<VendorDto> getVendorByBusinessName(String businessName) {
        List<VendorDto> vendorDtoList = new ArrayList<>();
        vendorRepository.searchForVendorByBusinessName(businessName)
                .forEach(vendor -> vendorDtoList.add(entityDtoConverter.convertVendorToDto(vendor)));
        return vendorDtoList;
    }

    @Override
    public VendorDto getVendorByUserId(Integer userId) {
        return entityDtoConverter.convertVendorToDto(
                vendorRepository.findById(userId).orElseThrow()
        );
    }

    @Override
    public Boolean updateVendorDetails(Integer userId, VendorDto vendorDto) {
        if (vendorRepository.findById(userId).isPresent()) {
            Vendor vendor = entityDtoConverter.convertDtoToVendor(vendorDto);
            vendor.setUserId(userId);
            vendorRepository.save(vendor);
            return true;
        }else return false;
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }else return false;
    }

    @Override
    public AccountStatus updateUserAccountStatus(Integer userId, AccountStatus accountStatus) {
        if (userRepository.findById(userId).isPresent()) {
            User user = userRepository.getReferenceById(userId);
            user.setAccountStatus(accountStatus);
            return user.getAccountStatus();
        }
        return AccountStatus.UNDEFINED;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerRepository.findAll().forEach(customer ->
                customerDtoList.add(entityDtoConverter.convertCustomerToDto(customer)));
        return customerDtoList;
    }

    @Override
    public Boolean updateVendorLocation(Integer userId, Location location) {
        if (vendorRepository.findById(userId).isPresent()) {
            Vendor vendor = vendorRepository.findById(userId).orElseThrow();
            vendor.setCurrentLocation(location);
            vendorRepository.save(vendor);
            return true;
        }else return null;
    }

    @Override
    public Location queryVendorLocation(Integer userId) {
        Vendor vendor = vendorRepository.findById(userId).orElseThrow();
        return vendor.getCurrentLocation();
    }

    @Override
    public CustomerDto getCustomerByUserId(Integer userId) {
        return entityDtoConverter.convertCustomerToDto(customerRepository.findById(userId).orElseThrow());
    }
}
