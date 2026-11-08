package com.fooddonation.service;

import com.fooddonation.dto.AuthResponse;
import com.fooddonation.dto.LoginRequest;
import com.fooddonation.dto.RegisterRequest;
import com.fooddonation.exception.BadRequestException;
import com.fooddonation.model.Role;
import com.fooddonation.model.User;
import com.fooddonation.repository.UserRepository;
import com.fooddonation.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// Registration and login (PB-01, PB-02, PB-29)
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest request) {
        if (request.role() == Role.ADMIN) {
            throw new BadRequestException("Administrator accounts cannot be created from registration");
        }
        String email = request.email().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException("This email is already registered");
        }

        User user = new User();
        user.setName(request.name().trim());
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(request.password()));   // never store the real password
        user.setPhone(request.phone());
        user.setRole(request.role());
        userRepository.save(user);

        return toResponse(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email().trim().toLowerCase())
                .orElseThrow(() -> new BadRequestException("Wrong email or password"));
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            throw new BadRequestException("Wrong email or password");
        }
        return toResponse(user);
    }

    private AuthResponse toResponse(User user) {
        return new AuthResponse(jwtUtil.generateToken(user), user.getName(), user.getEmail(), user.getRole());
    }
}
