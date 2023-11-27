package com.quickmeals.authservice.config;

import com.quickmeals.authservice.entities.User;
import com.quickmeals.authservice.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class AppConfig {
    private final UserRepository userRepository;

    public AppConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Optional<User> user = userRepository.findUserByUserName(username);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("Customer not found");
            }
            return user.orElseThrow();
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

//    @Value("${app.firebase-configuration-file}")
//    private Resource firebseAdminFile;
//
//    @Bean
//    public void initializeFirebaseApp() throws IOException {
//        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(firebseAdminFile.getInputStream()))
//                .build();
//        FirebaseApp.initializeApp(firebaseOptions);
//    }
}
