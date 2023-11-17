package com.quickmeals.authservice.serviceImpl;

import com.quickmeals.authservice.customtypes.*;
import com.quickmeals.authservice.dtos.*;
import com.quickmeals.authservice.entities.*;
import com.quickmeals.authservice.repository.CustomerRepository;
import com.quickmeals.authservice.repository.VendorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EntityDtoConverter implements com.quickmeals.authservice.services.EntityDtoConverter {
    private final CustomerRepository customerRepository;

    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;

    public EntityDtoConverter(CustomerRepository customerRepository, VendorRepository vendorRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto convertCustomerToUserDto(Customer customer) {
        UserDto userDto = new UserDto();
        userDto.setUserId(customer.getUserId());
        userDto.setUserName(customer.getUserName());
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
        userDto.setUserName(vendor.getUserName());
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
        userDto.setUserName(user.getUserName());
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
        List<Integer> previousOrderList = new ArrayList<>();
        customer.getPreviousOrdersList().forEach(order ->
                previousOrderList.add(order.getOrderId()));

        CustomerDto customerDto = new CustomerDto();
        customerDto.setUserId(customer.getUserId());
        customerDto.setUserName(customer.getUserName());
        customerDto.setFullName(customer.getFullName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setAddress(customer.getAddress());
        customerDto.setLatitude(customer.getCurrentLocation().getLatitude());
        customerDto.setLongitude(customer.getCurrentLocation().getLongitude());
        customerDto.setRole(String.valueOf(customer.getRole()));
        customerDto.setAccountStatus(String.valueOf(customer.getAccountStatus()));
        customerDto.setPreviousOrderIdList(previousOrderList);
        customerDto.setFavouriteFoodIdList(customer.getFavouriteFoodIdList());
        customerDto.setFavouriteVendorIdList(customer.getFavouriteVendorIdList());
        return customerDto;
    }

    @Override
    public Customer convertDtoToCustomer(CustomerDto customerDto) {
        Location currentLocation = new Location(customerDto.getLatitude(), customerDto.getLongitude());
        List<MealOrder> previousMealOrders = new ArrayList<>();
        customerDto.getPreviousOrderIdList().forEach(id ->
                previousMealOrders.add(mealOrderRepository.findById(id).orElseThrow()));

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
        customer.setPreviousOrdersList(previousMealOrders);
        customer.setFavouriteFoodIdList(customerDto.getFavouriteFoodIdList());
        customer.setFavouriteVendorIdList(customerDto.getFavouriteVendorIdList());
        return customer;
    }


    @Override
    public VendorDto convertVendorToDto(Vendor vendor) {
        List<Meal> meals = mealRepository.findMealsByVendor_UserId(vendor.getUserId()).orElseThrow();
        List<Integer> mealIds = new ArrayList<>();
        meals.forEach(meal -> mealIds.add(meal.getMealId()));

        List<MealOrder> orderList = mealOrderRepository.findOrderBySelectedVendor_UserId(vendor.getUserId()).orElseThrow();
        List<Integer> orderIds = new ArrayList<>();
        orderList.forEach(order -> orderIds.add(order.getOrderId()));

        return getVendorDto(vendor, mealIds, orderIds);
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
        vendor.setMeals(mealRepository.findMealsByVendor_UserName(vendorDto.getUserName()).orElseThrow());
        vendor.setOrderList(mealOrderRepository.findOrderByVendor_UserName(vendorDto.getUserName()).orElseThrow());
        return vendor;
    }
}
