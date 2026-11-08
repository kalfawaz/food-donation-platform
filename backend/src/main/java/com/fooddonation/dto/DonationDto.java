package com.fooddonation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

// Data the donor sends from the "New Donation" form (PB-05, PB-06, PB-28)
public record DonationDto(
        @NotBlank(message = "Food type is required") String foodType,
        @NotBlank(message = "Category is required") String category,
        @NotNull(message = "Quantity is required") @Positive(message = "Quantity must be a positive number") Integer quantity,
        @NotNull(message = "Preparation time is required") LocalDateTime preparedAt,
        @NotNull(message = "Expiry time is required") LocalDateTime expiresAt,
        @NotBlank(message = "Pickup location is required") String pickupLocation,
        String description
) {
}
