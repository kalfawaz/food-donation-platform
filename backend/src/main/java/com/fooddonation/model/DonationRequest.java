package com.fooddonation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Used in Sprint 2 (request and approve a donation)
@Entity
@Table(name = "donation_requests")
public class DonationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_id", nullable = false)
    private Donation donation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "charity_id", nullable = false)
    private User charity;

    private String note;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.PENDING;

    private LocalDateTime requestedAt = LocalDateTime.now();

    public Long getRequestId() { return requestId; }
    public Donation getDonation() { return donation; }
    public void setDonation(Donation donation) { this.donation = donation; }
    public User getCharity() { return charity; }
    public void setCharity(User charity) { this.charity = charity; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
    public LocalDateTime getRequestedAt() { return requestedAt; }
}
