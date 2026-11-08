package com.fooddonation.dto;

import com.fooddonation.model.Role;

// What the frontend receives after register or login
public record AuthResponse(String token, String name, String email, Role role) {
}
