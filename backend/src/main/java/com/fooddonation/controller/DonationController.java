package com.fooddonation.controller;

import com.fooddonation.dto.DonationDto;
import com.fooddonation.dto.DonationResponse;
import com.fooddonation.service.DonationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    // Only donors can create a donation (PB-03, PB-05)
    @PostMapping
    @PreAuthorize("hasRole('DONOR')")
    public ResponseEntity<DonationResponse> create(@Valid @RequestBody DonationDto dto, Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(donationService.createDonation(dto, principal.getName()));
    }

    // The logged-in donor's own donations
    @GetMapping("/mine")
    @PreAuthorize("hasRole('DONOR')")
    public List<DonationResponse> myDonations(Principal principal) {
        return donationService.getMyDonations(principal.getName());
    }

    // Available donations for charities (first version of PB-09)
    @GetMapping
    @PreAuthorize("hasAnyRole('CHARITY', 'ADMIN')")
    public List<DonationResponse> available() {
        return donationService.getAvailableDonations();
    }
}
