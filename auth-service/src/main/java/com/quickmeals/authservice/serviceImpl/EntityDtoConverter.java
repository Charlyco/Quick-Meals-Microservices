package com.quickmeals.authservice.serviceImpl;

import com.quickmeals.authservice.customtypes.*;
import com.quickmeals.authservice.dtos.*;
import com.quickmeals.authservice.entities.*;
import com.quickmeals.authservice.repository.CustomerRepository;
import com.quickmeals.authservice.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EntityDtoConverter implements com.quickmeals.authservice.services.EntityDtoConverter {
    private final CustomerRepository customerRepository;
    private final LoadBalancerClient loadBalancerClient;
    private final WebClient.Builder webClientBuilder;
    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;

    public Flux<Integer> getOrdersForVendor(Integer vendorId) {
        String orderServiceUrl = loadBalancerClient.choose("order-service").getUri().toString();
        return  webClientBuilder.build()
                .get()
                .uri(orderServiceUrl + "/api/v1/mealOrder/vendor?vendorId={vendorId}", vendorId)
                .retrieve()
                .bodyToFlux(MealOrderDto.class)
                .map(MealOrderDto::getOrderId);
    }

    public Flux<Integer> getMealByVendor(Integer vendorId) {
        String orderServiceUrl = loadBalancerClient.choose("product-service").getUri().toString();
        return  webClientBuilder.build()
                .get()
                .uri(orderServiceUrl + "/api/v1/meal/vendorId?vendorId={vendorId}", vendorId)
                .retrieve()
                .bodyToFlux(MealDto.class)
                .map(MealDto::getMealId);
    }

    @Override
    public UserDto convertCustomerToUserDto(Customer customer) {
        UserDto userDto = new UserDto();
        userDto.setUserId(customer.getUserId());
        userDto.setUserName(customer.getUsername());
        userDto.setFullName(customer.getFullName());
        userDto.setEmail(customer.getEmail());
        userDto.setPhoneNumber(customer.getPhoneNumber());
        userDto.setAddress(customer.getAddress());
        userDto.setLatitude(customer.getCurrentLocation().getLatitude());
        userDto.setLongitude(customer.getCurrentLocation().getLongitude());
        userDto.setRole(String.valueOf(customer.getRole()));
        userDto.setAccountStatus(String.valueOf(customer.getAccountStatus()));
        return userDto;
    }

    @Override
    public UserDto convertVendorToUserDto(Vendor vendor) {
        UserDto userDto = new UserDto();
        userDto.setUserId(vendor.getUserId());
        userDto.setUserName(vendor.getUsername());
        userDto.setFullName(vendor.getFullName());
        userDto.setEmail(vendor.getEmail());
        userDto.setPhoneNumber(vendor.getPhoneNumber());
        userDto.setAddress(vendor.getAddress());
        userDto.setLatitude(vendor.getCurrentLocation().getLatitude());
        userDto.setLongitude(vendor.getCurrentLocation().getLongitude());
        userDto.setRole(String.valueOf(vendor.getRole()));
        userDto.setAccountStatus(String.valueOf(vendor.getAccountStatus()));
        return userDto;
    }

    @Override
    public UserDto convertUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUsername());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setLatitude(user.getCurrentLocation().getLatitude());
        userDto.setLongitude(user.getCurrentLocation().getLongitude());
        userDto.setRole(String.valueOf(user.getRole()));
        userDto.setAccountStatus(String.valueOf(user.getAccountStatus()));
        return userDto;
    }

    @Override
    public CustomerDto convertCustomerToDto(Customer customer) {

        CustomerDto customerDto = new CustomerDto();

        customerDto.setUserId(customer.getUserId());
        customerDto.setUserName(customer.getUsername());
        customerDto.setFullName(customer.getFullName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setAddress(customer.getAddress());
        customerDto.setLatitude(customer.getCurrentLocation().getLatitude());
        customerDto.setLongitude(customer.getCurrentLocation().getLongitude());
        customerDto.setRole(String.valueOf(customer.getRole()));
        customerDto.setAccountStatus(String.valueOf(customer.getAccountStatus()));
        customerDto.setPreviousOrderIdList(customer.getPreviousOrdersList());
        customerDto.setFavouriteFoodIdList(customer.getFavouriteFoodIdList());
        customerDto.setFavouriteVendorIdList(customer.getFavouriteVendorIdList());
        return customerDto;
    }

    @Override
    public Customer convertDtoToCustomer(CustomerDto customerDto) {
        Location currentLocation = new Location(customerDto.getLatitude(), customerDto.getLongitude());

        Customer customer = new Customer();
        customer.setUserName(customerDto.getUserName());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setFullName(customerDto.getFullName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setCurrentLocation(currentLocation);
        customer.setRole(Role.valueOf(customerDto.getRole()));
        customer.setAccountStatus(AccountStatus.valueOf(customerDto.getAccountStatus()));
        customer.setFavouriteFoodIdList(customerDto.getFavouriteFoodIdList());
        customer.setFavouriteVendorIdList(customerDto.getFavouriteVendorIdList());
        return customer;
    }


    @Override
    public VendorDto convertVendorToDto(Vendor vendor) {
        List<Integer> mealIds = new ArrayList<>();
        getMealByVendor(vendor.getUserId()).collectList()
                .subscribe(mealIds::addAll);

        List<Integer> orderIdList = new ArrayList<>();
        getOrdersForVendor(vendor.getUserId()).collectList()
                .subscribe(orderIdList::addAll);
        return getVendorDto(vendor, mealIds, orderIdList);
    }

    private static VendorDto getVendorDto(Vendor vendor, List<Integer> mealIds, List<Integer> orderIds) {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setUserId(vendor.getUserId());
        vendorDto.setUserName(vendor.getUsername());
        vendorDto.setBusinessName(vendor.getBusinessName());
        vendorDto.setManagerFullName(vendor.getFullName());
        vendorDto.setAddress(vendor.getAddress());
        vendorDto.setEmail(vendor.getEmail());
        vendorDto.setPhoneNumber(vendor.getPhoneNumber());
        vendorDto.setBusinessDescription(vendor.getBusinessDescription());
        vendorDto.setRole(String.valueOf(vendor.getRole()));
        vendorDto.setAccountStatus(String.valueOf(vendor.getAccountStatus()));
        vendorDto.setRating(vendor.getRating());
        vendorDto.setTotalSales(vendor.getTotalSales());
        vendorDto.setWalletBalance(vendor.getWalletBalance());
        vendorDto.setBankName(vendor.getBankDetail().getBankName());
        vendorDto.setAccountName(vendor.getBankDetail().getAccountName());
        vendorDto.setAccountNumber(vendor.getBankDetail().getAccountNumber());
        vendorDto.setLatitude(vendor.getCurrentLocation().getLatitude());
        vendorDto.setLongitude(vendor.getCurrentLocation().getLongitude());
        vendorDto.setMealIdList(mealIds);
        vendorDto.setMealOrderIdList(orderIds);
        return vendorDto;
    }

    @Override
    public Vendor convertDtoToVendor(VendorDto vendorDto) {
        Location location = new Location();
        location.setLatitude(vendorDto.getLatitude());
        location.setLongitude(vendorDto.getLongitude());
        BankDetail bankDetail = new BankDetail();
        bankDetail.setBankName(vendorDto.getBankName());
        bankDetail.setAccountName(vendorDto.getAccountName());
        bankDetail.setAccountNumber(vendorDto.getAccountNumber());

        Vendor vendor = new Vendor();
        if (vendorDto.getUserId() != null) {
            vendor.setUserId(vendorDto.getUserId());
        }
        vendor.setUserName(vendorDto.getUserName());
        vendor.setPassword(passwordEncoder.encode(vendor.getPassword()));
        vendor.setBusinessName(vendorDto.getBusinessName());
        vendor.setFullName(vendorDto.getManagerFullName());
        vendor.setAddress(vendorDto.getAddress());
        vendor.setEmail(vendorDto.getEmail());
        vendor.setPhoneNumber(vendorDto.getPhoneNumber());
        vendor.setBusinessDescription(vendor.getBusinessDescription());
        vendor.setRole(Role.valueOf(vendorDto.getRole()));
        vendor.setAccountStatus(AccountStatus.valueOf(vendorDto.getAccountStatus()));
        vendor.setRating(vendorDto.getRating());
        vendor.setCurrentLocation(location);
        return vendor;
    }
}
