import { Component, inject, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { DonationService } from '../../services/donation.service';
import { Donation } from '../../models';

// First version of PB-09 (browse). Request, filters and details come in Sprint 2.
@Component({
  selector: 'app-available-donations',
  imports: [DatePipe],
  templateUrl: './available-donations.html'
})
export class AvailableDonations implements OnInit {
  private donationService = inject(DonationService);

  donations: Donation[] = [];
  error = '';

  ngOnInit(): void {
    this.donationService.getAvailable().subscribe({
      next: list => this.donations = list,
      error: () => this.error = 'Could not load donations'
    });
  }
}
