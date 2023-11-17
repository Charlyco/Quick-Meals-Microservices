package com.quickmeals.authservice.serviceImpl;

import com.quickmeals.authservice.customtypes.AuthResponse;
import com.quickmeals.authservice.dtos.CustomerDto;
import com.quickmeals.authservice.dtos.VendorDto;
import com.quickmeals.authservice.entities.AuthToken;
import com.quickmeals.authservice.entities.Customer;
import com.quickmeals.authservice.entities.User;
import com.quickmeals.authservice.entities.Vendor;
import com.quickmeals.authservice.repository.CustomerRepository;
import com.quickmeals.authservice.repository.TokenRepository;
import com.quickmeals.authservice.repository.UserRepository;
import com.quickmeals.authservice.repository.VendorRepository;
import com.quickmeals.authservice.security.JwtService;
import com.quickmeals.authservice.services.AuthService;
import com.quickmeals.authservice.services.EntityDtoConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

public class AuthServiceImpl implements AuthService {
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final EntityDtoConverter entityDtoConverter;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            CustomerRepository customerRepository,
            VendorRepository vendorRepository,
            JwtService jwtService,
            TokenRepository tokenRepository,
            UserRepository userRepository, EntityDtoConverter entityDtoConverter, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.entityDtoConverter = entityDtoConverter;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param customerDto
     * @return
     */
    @Override
    public AuthResponse createCustomer(CustomerDto customerDto) {
        List<User> allUsers = userRepository.findAll();
        if (allUsers.stream().noneMatch(user -> Objects.equals(user.getUserName(), customerDto.getUserName()))) {
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
        if (allUsers.stream().noneMatch(user -> Objects.equals(user.getUserName(), vendorDto.getUserName()))) {
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
    public AuthResponse signIn(String userName, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userName, password
            )
        );
        User authenicatedUser = userRepository.findUserByUser_UserName(userName).orElseThrow();
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

    @Override
    public Boolean resetPassword(String userName, String phone, String password) {
        if (userRepository.findUserByUser_UserName(userName).isPresent()) {
            var user = userRepository.findUserByUser_UserName(userName).orElseThrow();
            if (Objects.equals(user.getPhoneNumber(), phone)) {
                user.setPassword(passwordEncoder.encode(password));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

}
