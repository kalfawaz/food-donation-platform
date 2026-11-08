import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { Role } from '../../models';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html'
})
export class Register {
  private auth = inject(AuthService);
  private router = inject(Router);

  // Administrators are not created from this page
  roles: { value: Role; label: string }[] = [
    { value: 'DONOR', label: 'Food Donor' },
    { value: 'CHARITY', label: 'Charity' },
    { value: 'VOLUNTEER', label: 'Volunteer' },
  ];

  form = { name: '', email: '', password: '', phone: '', role: 'DONOR' as Role };
  error = '';
  loading = false;

  submit(): void {
    this.error = '';
    this.loading = true;
    this.auth.register(this.form).subscribe({
      next: user => {
        this.loading = false;
        this.router.navigate([user.role === 'DONOR' ? '/donations/mine' : '/donations']);
      },
      error: err => {
        this.loading = false;
        this.error = err.error?.message ?? 'Could not connect to the server';
      }
    });
  }
}
