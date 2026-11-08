import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html'
})
export class Login {
  private auth = inject(AuthService);
  private router = inject(Router);

  email = '';
  password = '';
  error = '';
  loading = false;

  submit(): void {
    this.error = '';
    this.loading = true;
    this.auth.login(this.email, this.password).subscribe({
      next: user => {
        this.loading = false;
        // Send each role to its main page
        this.router.navigate([user.role === 'DONOR' ? '/donations/mine' : '/donations']);
      },
      error: err => {
        this.loading = false;
        this.error = err.error?.message ?? 'Could not connect to the server';
      }
    });
  }
}
