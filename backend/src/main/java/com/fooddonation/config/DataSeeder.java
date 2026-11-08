package com.fooddonation.config;

import com.fooddonation.model.Role;
import com.fooddonation.model.User;
import com.fooddonation.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// Creates one administrator account the first time the app runs
// Email: admin@fooddonation.com   Password: admin123   (change it before the final demo)
@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("admin@fooddonation.com")) {
            User admin = new User();
            admin.setName("Administrator");
            admin.setEmail("admin@fooddonation.com");
            admin.setPasswordHash(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
