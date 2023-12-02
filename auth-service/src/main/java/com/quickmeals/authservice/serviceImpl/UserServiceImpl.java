package com.quickmeals.authservice.serviceImpl;

import com.quickmeals.authservice.customtypes.AccountStatus;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.DispatcherDto;
import com.quickmeals.authservice.dtos.VendorDto;
import com.quickmeals.authservice.entities.Customer;
import com.quickmeals.authservice.entities.Dispatcher;
import com.quickmeals.authservice.entities.User;
import com.quickmeals.authservice.entities.Vendor;
import com.quickmeals.authservice.repository.CustomerRepository;
import com.quickmeals.authservice.repository.DispatcherRepository;
import com.quickmeals.authservice.repository.UserRepository;
import com.quickmeals.authservice.repository.VendorRepository;
import com.quickmeals.authservice.services.EntityDtoConverter;
import com.quickmeals.authservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final UserRepository userRepository;
    private final EntityDtoConverter entityDtoConverter;
    private final DispatcherRepository dispatcherRepository;

    @Override
    public List<VendorDto> getAllVendors() {
        List<VendorDto> vendorDtoList = new ArrayList<>();
        vendorRepository.findAll().forEach(vendor ->
                vendorDtoList.add(entityDtoConverter.convertVendorToDto(vendor)));
        return vendorDtoList;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        customerRepository.findAll().forEach(customer ->
                customerDtoList.add(entityDtoConverter.convertCustomerToDto(customer)));
        return customerDtoList;
    }
    @Override
    public List<DispatcherDto> getAllDispatchers() {
        List<DispatcherDto> dispatcherDtoList = new ArrayList<>();
        dispatcherRepository.findAll().forEach(dispatcher ->
                dispatcherDtoList.add(entityDtoConverter.convertDispatcherToDto(dispatcher)));
        return dispatcherDtoList;
    }
    @Override
    public List<VendorDto> getVendorByBusinessName(String businessName) {
        List<VendorDto> vendorDtoList = new ArrayList<>();
        vendorRepository.searchForVendorByBusinessName(businessName)
                .forEach(vendor -> vendorDtoList.add(entityDtoConverter.convertVendorToDto(vendor)));
        return vendorDtoList;
    }

    @Override
    public DispatcherDto getDispatcherByDispatcherTag(String dispatcherTag) {
        Dispatcher dispatcher = dispatcherRepository.findDispatcherByDispatcherTag(dispatcherTag).orElseThrow();
        return entityDtoConverter.convertDispatcherToDto(dispatcher);
    }

    @Override
    public VendorDto getVendorByUserId(Integer userId) {
        return entityDtoConverter.convertVendorToDto(
                vendorRepository.findById(userId).orElseThrow()
        );
    }

    @Override
    public Boolean updateVendorDetails(Integer userId, VendorDto vendorDto) {
        if (vendorRepository.existsById(userId)) {
            Vendor vendor = entityDtoConverter.convertDtoToVendor(vendorDto);
            vendor.setUserId(userId);
            vendorRepository.save(vendor);
            return true;
        }else return false;
    }

    @Override
    public Boolean updateCustomerDetails(Integer userId, CustomerDto customerDto) {
        if (customerRepository.existsById(userId)) {
            Customer customer = entityDtoConverter.convertDtoToCustomer(customerDto);
            customer.setUserId(userId);
            customerRepository.save(customer);
            return true;
        }else return false;
    }

    @Override
    public Boolean updateDispatcherDetails(Integer userId, DispatcherDto dispatcherDto) {
        if (dispatcherRepository.existsById(userId)) {
            Dispatcher dispatcher = entityDtoConverter.convertDtoToDispatcher(dispatcherDto);
            dispatcherRepository.save(dispatcher);
            return  true;
        } else return false;
    }

    @Override
    public Boolean deleteUser(Integer userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }else return false;
    }

    @Override
    public AccountStatus updateUserAccountStatus(Integer userId, String accountStatus) {
        if (userRepository.findById(userId).isPresent()) {
            User user = userRepository.getReferenceById(userId);
            user.setAccountStatus(AccountStatus.valueOf(accountStatus));
            return user.getAccountStatus();
        }
        return AccountStatus.UNDEFINED;
    }

    @Override
    public Double creditVendorWallet(Integer vendorId, Double amount) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();
        Double newWalletBalance = vendor.getWalletBalance() + amount;
        vendor.setWalletBalance(newWalletBalance);
        vendorRepository.save(vendor);
        return vendorRepository.findById(vendorId).orElseThrow().getWalletBalance();
    }

    @Override
    public Double debitVendorWallet(Integer vendorId, Double amount) {
        Vendor vendor = vendorRepository.findById(vendorId).orElseThrow();
        Double newWalletBalance = vendor.getWalletBalance() - amount;
        vendor.setWalletBalance(newWalletBalance);
        vendorRepository.save(vendor);
        return vendorRepository.findById(vendorId).orElseThrow().getWalletBalance();
    }

    @Override
    public Double creditDispatcherWallet(Integer dispatcherId, Double amount) {
        Dispatcher dispatcher = dispatcherRepository.findById(dispatcherId).orElseThrow();
        Double newWalletBalance = dispatcher.getWalletBalance() + amount;
        dispatcher.setWalletBalance(newWalletBalance);
        dispatcherRepository.save(dispatcher);
        return dispatcherRepository.findById(dispatcherId).orElseThrow().getWalletBalance();
    }

    @Override
    public Double debitDispatcherWallet(Integer dispatcherId, Double amount) {
        Dispatcher dispatcher = dispatcherRepository.findById(dispatcherId).orElseThrow();
        Double newWalletBalance = dispatcher.getWalletBalance() - amount;
        dispatcher.setWalletBalance(newWalletBalance);
        dispatcherRepository.save(dispatcher);
        return dispatcherRepository.findById(dispatcherId).orElseThrow().getWalletBalance();
    }

    @Override
    public CustomerDto getCustomerByUserId(Integer userId) {
        return entityDtoConverter.convertCustomerToDto(customerRepository.findById(userId).orElseThrow());
    }

    @Override
    public DispatcherDto getDispatcherByUserId(Integer userId) {
        return entityDtoConverter.convertDispatcherToDto(dispatcherRepository.findById(userId).orElseThrow());
    }
}
