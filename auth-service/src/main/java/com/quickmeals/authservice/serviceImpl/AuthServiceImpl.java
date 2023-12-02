package com.quickmeals.authservice.serviceImpl;

import com.quickmeals.authservice.customtypes.AuthResponse;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.DispatcherDto;
import com.quickmeals.authservice.dtos.VendorDto;
import com.quickmeals.authservice.entities.*;
import com.quickmeals.authservice.repository.*;
import com.quickmeals.authservice.security.JwtService;
import com.quickmeals.authservice.services.AuthService;
import com.quickmeals.authservice.services.EntityDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final DispatcherRepository dispatcherRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EntityDtoConverter entityDtoConverter;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    /**
     * @param customerDto
     * @return
     */
    @Override
    public AuthResponse createCustomer(CustomerDto customerDto) {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.stream().noneMatch(user -> Objects.equals(user.getUsername(), customerDto.getUserName()))) {
            if (allUsers.stream().noneMatch(user -> Objects.equals(user.getEmail(), customerDto.getEmail()))) {
                if (allUsers.stream().noneMatch(user -> Objects.equals(user.getPhoneNumber(), customerDto.getPhoneNumber()))) {
                    Customer createdCustomer = customerRepository.save(entityDtoConverter.convertDtoToCustomer(customerDto));
                    String authToken = jwtService.generateToken(createdCustomer);
                    saveToken(createdCustomer, authToken);
                    AuthResponse authResponse = new AuthResponse();
                    authResponse.setJwtToken(authToken);
                    authResponse.setUserDto(entityDtoConverter.convertCustomerToUserDto(createdCustomer));
                    return authResponse;
                }
            }
        }
        return new AuthResponse();
    }

    @Override
    public AuthResponse createVendor(VendorDto vendorDto) {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.stream().noneMatch(user -> Objects.equals(user.getUsername(), vendorDto.getUserName()))) {
            if (allUsers.stream().noneMatch(user -> Objects.equals(user.getEmail(), vendorDto.getEmail()))) {
                if (allUsers.stream().noneMatch(user -> Objects.equals(user.getPhoneNumber(), vendorDto.getPhoneNumber()))) {
                    Vendor createdVendor = vendorRepository.save(entityDtoConverter.convertDtoToVendor(vendorDto));
                    String authToken = jwtService.generateToken(createdVendor);
                    saveToken(createdVendor, authToken);
                    AuthResponse authResponse = new AuthResponse();
                    authResponse.setJwtToken(authToken);
                    authResponse.setUserDto(entityDtoConverter.convertVendorToUserDto(createdVendor));
                    return authResponse;
                }
            }
        }
        return null;
    }

    @Override
    public AuthResponse createDispatcher(DispatcherDto dispatcherDto) {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.stream().noneMatch(user -> Objects.equals(user.getUsername(), dispatcherDto.getUserName()))) {
            if (allUsers.stream().noneMatch(user -> Objects.equals(user.getEmail(), dispatcherDto.getEmail()))) {
                if (allUsers.stream().noneMatch(user -> Objects.equals(user.getPhoneNumber(), dispatcherDto.getPhoneNumber()))) {
                     Dispatcher newDispatcher = dispatcherRepository.save(entityDtoConverter.convertDtoToDispatcher(dispatcherDto));
                    String authToken = jwtService.generateToken(newDispatcher);
                    saveToken(newDispatcher, authToken);
                    AuthResponse authResponse = new AuthResponse();
                    authResponse.setJwtToken(authToken);
                    authResponse.setUserDto(entityDtoConverter.convertDispatcerToUserDto(newDispatcher));
                    return authResponse;
                }
            }
        }
        return null;
    }

    @Override
    public AuthResponse signIn(String userName, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userName, password
            )
        );
        User authenicatedUser = userRepository.findUserByUserName(userName).orElseThrow();
        String authToken = jwtService.generateToken(authenicatedUser);
        saveToken(authenicatedUser, authToken);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(authToken);
        authResponse.setUserDto(entityDtoConverter.convertUserToUserDto(authenicatedUser));
        return authResponse;
    }

    @Override
    public void signOut(String token) {
        revokeToken(token);
        AuthToken tokenToDelete = tokenRepository.findAuthTokenByToken(token).orElseThrow();
        tokenRepository.delete(tokenToDelete);
    }

    private void saveToken(User user, String jwtToken) {
        AuthToken token = new AuthToken();
        token.setToken(jwtToken);
        token.setUserId(user.getUserId());
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

    public void revokeToken(String token) {
        AuthToken tokenToRevoke = tokenRepository.findAuthTokenByToken(token).orElseThrow();
        tokenToRevoke.setRevoked(true);
        tokenRepository.save(tokenToRevoke);
    }

    /*
    * To be modified to include OTP verification for phone number*/
    @Override
    public Boolean resetPassword(String userName, String phone, String password) {
        if (userRepository.findUserByUserName(userName).isPresent()) {
            var user = userRepository.findUserByUserName(userName).orElseThrow();
            if (Objects.equals(user.getPhoneNumber(), phone)) {
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

}
