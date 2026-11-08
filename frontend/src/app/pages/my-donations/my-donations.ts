import { Component, inject, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';
import { DonationService } from '../../services/donation.service';
import { Donation } from '../../models';

@Component({
  selector: 'app-my-donations',
  imports: [DatePipe, RouterLink],
  templateUrl: './my-donations.html'
})
export class MyDonations implements OnInit {
  private donationService = inject(DonationService);

  donations: Donation[] = [];
  error = '';

  ngOnInit(): void {
    this.donationService.getMyDonations().subscribe({
      next: list => this.donations = list,
      error: () => this.error = 'Could not load your donations'
    });
  }
}
