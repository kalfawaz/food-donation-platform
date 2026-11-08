package com.fooddonation.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// One row for every status change of a donation (traceability, NFR-06)
@Entity
@Table(name = "status_history")
public class StatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_id", nullable = false)
    private Donation donation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by", nullable = false)
    private User changedBy;

    @Enumerated(EnumType.STRING)
    private DonationStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DonationStatus newStatus;

    private LocalDateTime changedAt = LocalDateTime.now();

    protected StatusHistory() {
    }

    public StatusHistory(Donation donation, DonationStatus oldStatus, DonationStatus newStatus, User changedBy) {
        this.donation = donation;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
    }

    public Long getHistoryId() { return historyId; }
    public Donation getDonation() { return donation; }
    public User getChangedBy() { return changedBy; }
    public DonationStatus getOldStatus() { return oldStatus; }
    public DonationStatus getNewStatus() { return newStatus; }
    public LocalDateTime getChangedAt() { return changedAt; }
}
