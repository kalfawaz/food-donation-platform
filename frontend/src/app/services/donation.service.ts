import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { API_URL } from '../api';
import { Donation } from '../models';

@Injectable({ providedIn: 'root' })
export class DonationService {
  constructor(private http: HttpClient) {}

  create(donation: Donation): Observable<Donation> {
    return this.http.post<Donation>(`${API_URL}/donations`, donation);
  }

  getMyDonations(): Observable<Donation[]> {
    return this.http.get<Donation[]>(`${API_URL}/donations/mine`);
  }

  getAvailable(): Observable<Donation[]> {
    return this.http.get<Donation[]>(`${API_URL}/donations`);
  }
}
