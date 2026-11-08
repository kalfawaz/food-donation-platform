package com.fooddonation.service;

import com.fooddonation.dto.DonationDto;
import com.fooddonation.dto.DonationResponse;
import com.fooddonation.exception.BadRequestException;
import com.fooddonation.model.Donation;
import com.fooddonation.model.DonationStatus;
import com.fooddonation.model.StatusHistory;
import com.fooddonation.model.User;
import com.fooddonation.repository.DonationRepository;
import com.fooddonation.repository.StatusHistoryRepository;
import com.fooddonation.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final StatusHistoryRepository historyRepository;
    private final UserRepository userRepository;

    public DonationService(DonationRepository donationRepository, StatusHistoryRepository historyRepository,
                           UserRepository userRepository) {
        this.donationRepository = donationRepository;
        this.historyRepository = historyRepository;
        this.userRepository = userRepository;
    }

    // PB-05, PB-06: create a donation. Date rules from the acceptance criteria (Section 4.3)
    @Transactional
    public DonationResponse createDonation(DonationDto dto, String donorEmail) {
        if (!dto.expiresAt().isAfter(dto.preparedAt())) {
            throw new BadRequestException("Expiry time must be after preparation time");
        }
        if (!dto.expiresAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("This food has already expired");
        }

        User donor = findUser(donorEmail);
        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setFoodType(dto.foodType().trim());
        donation.setCategory(dto.category());
        donation.setQuantity(dto.quantity());
        donation.setPreparedAt(dto.preparedAt());
        donation.setExpiresAt(dto.expiresAt());
        donation.setPickupLocation(dto.pickupLocation().trim());
        donation.setDescription(dto.description());
        donation.setStatus(DonationStatus.AVAILABLE);

        Donation saved = donationRepository.save(donation);
        historyRepository.save(new StatusHistory(saved, null, DonationStatus.AVAILABLE, donor));
        return DonationResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public List<DonationResponse> getMyDonations(String donorEmail) {
        return donationRepository.findByDonorOrderByCreatedAtDesc(findUser(donorEmail))
                .stream().map(DonationResponse::from).toList();
    }

    // Expired donations are not shown (they are hidden from the list)
    @Transactional(readOnly = true)
    public List<DonationResponse> getAvailableDonations() {
        return donationRepository
                .findByStatusAndExpiresAtAfterOrderByExpiresAtAsc(DonationStatus.AVAILABLE, LocalDateTime.now())
                .stream().map(DonationResponse::from).toList();
    }

    private User findUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found"));
    }
}
