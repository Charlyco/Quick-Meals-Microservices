package com.quickmeals.authservice.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickmeals.authservice.customtypes.*;
import com.quickmeals.authservice.dtos.*;
import com.quickmeals.authservice.entities.*;
import com.quickmeals.authservice.repository.CustomerRepository;
import com.quickmeals.authservice.repository.VendorRepository;
import com.quickmeals.authservice.services.EntityDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class EntityDtoConverterImpl implements EntityDtoConverter {
    private final CustomerRepository customerRepository;
    private final LoadBalancerClient loadBalancerClient;
    private final VendorRepository vendorRepository;
    private final PasswordEncoder passwordEncoder;

    public <T> List<T> deserializeJsonArray(String jsonArray, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonArray, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            log.info(e.getMessage());
            return null; // Handle exception or return an empty list based on your needs
        }
    }
    public List<Integer> getOrderIdsForVendor(Integer vendorId) {
        String orderServiceUrl = loadBalancerClient.choose("order-service").getUri().toString();
        RestClient restClient = RestClient.create();
        String body = restClient.get()
                .uri(orderServiceUrl + "/api/v1/mealOrder/vendor?vendorId={vendorId}", vendorId)
                .retrieve()
                .body(String.class);
        return deserializeJsonArray(body, MealOrderDto.class).stream().map(MealOrderDto::getOrderId).toList();
    }
    public List<Integer> getMealIdsByVendor(Integer vendorId) {
        String mealServiceUrl = loadBalancerClient.choose("product-service").getUri().toString();
        RestClient restClient = RestClient.create();
        String body = restClient.get()
                .uri(mealServiceUrl + "/api/v1/meal/vendorId?vendorId={vendorId}", vendorId)
                .retrieve()
                .body(String.class);
        return deserializeJsonArray(body, MealDto.class).stream().map(MealDto::getMealId).toList();
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
    public UserDto convertDispatcerToUserDto(Dispatcher dispatcher) {
        UserDto userDto = new UserDto();
        userDto.setUserId(dispatcher.getUserId());
        userDto.setUserName(dispatcher.getUsername());
        userDto.setFullName(dispatcher.getFullName());
        userDto.setEmail(dispatcher.getEmail());
        userDto.setPhoneNumber(dispatcher.getPhoneNumber());
        userDto.setAddress(dispatcher.getAddress());
        userDto.setLatitude(dispatcher.getCurrentLocation().getLatitude());
        userDto.setLongitude(dispatcher.getCurrentLocation().getLongitude());
        userDto.setRole(String.valueOf(dispatcher.getRole()));
        userDto.setAccountStatus(String.valueOf(dispatcher.getAccountStatus()));
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
        List<Integer> mealIds = getMealIdsByVendor(vendor.getUserId());

        List<Integer> orderIdList = getOrderIdsForVendor(vendor.getUserId());
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
        vendor.setPassword(passwordEncoder.encode(vendorDto.getPassword()));
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

    @Override
    public Dispatcher convertDtoToDispatcher(DispatcherDto dispatcherDto) {
        Location location = new Location();
        location.setLatitude(dispatcherDto.getLatitude());
        location.setLongitude(dispatcherDto.getLongitude());
        BankDetail bankDetail = new BankDetail();
        bankDetail.setBankName(dispatcherDto.getBankName());
        bankDetail.setAccountName(dispatcherDto.getAccountName());
        bankDetail.setAccountNumber(dispatcherDto.getAccountNumber());

        Dispatcher dispatcher = new Dispatcher();

        dispatcher.setUserName(dispatcherDto.getUserName());
        dispatcher.setPassword(passwordEncoder.encode(dispatcherDto.getPassword()));
        dispatcher.setAddress(dispatcherDto.getAddress());
        dispatcher.setEmail(dispatcherDto.getEmail());
        dispatcher.setPhoneNumber(dispatcherDto.getPhoneNumber());
        dispatcher.setRole(Role.valueOf(dispatcherDto.getRole()));
        dispatcher.setAccountStatus(AccountStatus.valueOf(dispatcherDto.getAccountStatus()));
        dispatcher.setRating(dispatcherDto.getRating());
        dispatcher.setDailyDeliveries(dispatcher.getDailyDeliveries());
        dispatcher.setBankDetail(bankDetail);
        dispatcher.setCurrentLocation(location);
        return dispatcher;
    }

    @Override
    public DispatcherDto convertDispatcherToDto(Dispatcher dispatcher) {
        DispatcherDto dispatcherDto = new DispatcherDto();
        dispatcherDto.setUserId(dispatcher.getUserId());
        dispatcherDto.setUserName(dispatcher.getUsername());
        dispatcherDto.setDispatcherTag(dispatcher.getDispatcherTag());
        dispatcherDto.setEmail(dispatcherDto.getEmail());
        dispatcherDto.setPhoneNumber(dispatcher.getPhoneNumber());
        dispatcherDto.setLatitude(dispatcher.getCurrentLocation().getLatitude());
        dispatcherDto.setLongitude(dispatcher.getCurrentLocation().getLongitude());
        dispatcherDto.setAddress(dispatcher.getAddress());
        dispatcherDto.setRole(String.valueOf(dispatcher.getRole()));
        dispatcherDto.setRating(dispatcher.getRating());
        dispatcherDto.setAccountStatus(String.valueOf(dispatcher.getAccountStatus()));
        dispatcherDto.setDailyDeliveries(dispatcher.getDailyDeliveries());
        dispatcherDto.setWalletBalance(dispatcher.getWalletBalance());
        return null;
    }
}
