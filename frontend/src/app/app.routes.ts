import { Routes } from '@angular/router';
import { roleGuard } from './guards/role.guard';
import { Login } from './pages/login/login';
import { Register } from './pages/register/register';
import { NewDonation } from './pages/new-donation/new-donation';
import { MyDonations } from './pages/my-donations/my-donations';
import { AvailableDonations } from './pages/available-donations/available-donations';

export const routes: Routes = [
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'donations/new', component: NewDonation, canActivate: [roleGuard], data: { roles: ['DONOR'] } },
  { path: 'donations/mine', component: MyDonations, canActivate: [roleGuard], data: { roles: ['DONOR'] } },
  { path: 'donations', component: AvailableDonations, canActivate: [roleGuard], data: { roles: ['CHARITY', 'ADMIN'] } },
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: '**', redirectTo: 'login' },
];
