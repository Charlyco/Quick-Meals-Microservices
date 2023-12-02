package com.quickmeals.authservice;

import com.quickmeals.authservice.controllers.AuthController;
import com.quickmeals.authservice.customtypes.AuthResponse;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.entities.Customer;
import com.quickmeals.authservice.services.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthControllerTests {
    @Autowired
    private AuthController authController;
    @Autowired
    private AuthService authService;
    @Test
    void shouldCreateNewUser() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setUserName("Charlyco");
        customerDto.setPassword("password123");
        customerDto.setFullName("Onuoha Chukwuemeka");
        customerDto.setEmail("charlyco84@yahoo.com");
        customerDto.setPhoneNumber("07037590923");
        customerDto.setAddress("Achuzilam Street Nekede");
        customerDto.setLatitude(3.4656);
        customerDto.setLongitude(8.6537);
        customerDto.setRole("CUSTOMER");
        customerDto.setAccountStatus("PENDING");
        customerDto.setPreviousOrderIdList(new ArrayList<>());
        customerDto.setFavouriteFoodIdList(new ArrayList<>());
        customerDto.setFavouriteVendorIdList(new ArrayList<>());

        AuthResponse newCustomer = authService.createCustomer(customerDto);
        assertThat(newCustomer.getUserDto().getUserId()).isEqualTo(1);
    }
}