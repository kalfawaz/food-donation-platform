package com.fooddonation.repository;

import com.fooddonation.model.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRequestRepository extends JpaRepository<DonationRequest, Long> {
}
