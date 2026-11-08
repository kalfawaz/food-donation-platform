package com.fooddonation.repository;

import com.fooddonation.model.Donation;
import com.fooddonation.model.DonationStatus;
import com.fooddonation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByDonorOrderByCreatedAtDesc(User donor);

    // Available donations that have not expired yet, closest expiry first
    List<Donation> findByStatusAndExpiresAtAfterOrderByExpiresAtAsc(DonationStatus status, LocalDateTime now);
}
