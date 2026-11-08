package com.fooddonation.dto;

import com.fooddonation.model.Donation;
import com.fooddonation.model.DonationStatus;

import java.time.LocalDateTime;

// What the frontend receives for a donation (never exposes the donor's password or email)
public record DonationResponse(
        Long donationId,
        String foodType,
        String category,
        int quantity,
        LocalDateTime preparedAt,
        LocalDateTime expiresAt,
        String pickupLocation,
        String description,
        DonationStatus status,
        String donorName,
        LocalDateTime createdAt
) {
    public static DonationResponse from(Donation d) {
        return new DonationResponse(d.getDonationId(), d.getFoodType(), d.getCategory(), d.getQuantity(),
                d.getPreparedAt(), d.getExpiresAt(), d.getPickupLocation(), d.getDescription(),
                d.getStatus(), d.getDonor().getName(), d.getCreatedAt());
    }
}
