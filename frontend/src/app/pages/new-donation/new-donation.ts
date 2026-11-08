import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { DonationService } from '../../services/donation.service';
import { Donation } from '../../models';

@Component({
  selector: 'app-new-donation',
  imports: [FormsModule],
  templateUrl: './new-donation.html'
})
export class NewDonation {
  private donations = inject(DonationService);
  private router = inject(Router);

  categories = ['Cooked meals', 'Bakery', 'Fruits and vegetables', 'Dairy', 'Packaged food', 'Other'];

  donation: Donation = {
    foodType: '', category: '', quantity: null,
    preparedAt: '', expiresAt: '', pickupLocation: '', description: ''
  };
  error = '';
  loading = false;

  submit(): void {
    this.error = '';
    // Quick check in the browser; the server checks again (Section 4.3 acceptance criteria)
    if (new Date(this.donation.expiresAt) <= new Date(this.donation.preparedAt)) {
      this.error = 'Expiry time must be after preparation time';
      return;
    }
    this.loading = true;
    this.donations.create(this.donation).subscribe({
      next: () => this.router.navigate(['/donations/mine']),
      error: err => {
        this.loading = false;
        this.error = err.error?.message ?? 'Could not save the donation';
      }
    });
  }
}
