import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { API_URL } from '../api';
import { AuthResponse, Role } from '../models';

const STORAGE_KEY = 'fdp_user';

@Injectable({ providedIn: 'root' })
export class AuthService {
  // The logged-in user (null when nobody is logged in)
  readonly currentUser = signal<AuthResponse | null>(this.loadUser());

  constructor(private http: HttpClient) {}

  register(data: { name: string; email: string; password: string; phone: string; role: Role }): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${API_URL}/auth/register`, data).pipe(tap(user => this.saveUser(user)));
  }

  login(email: string, password: string): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${API_URL}/auth/login`, { email, password }).pipe(tap(user => this.saveUser(user)));
  }

  logout(): void {
    localStorage.removeItem(STORAGE_KEY);
    this.currentUser.set(null);
  }

  getToken(): string | null {
    return this.currentUser()?.token ?? null;
  }

  hasRole(...roles: Role[]): boolean {
    const user = this.currentUser();
    return !!user && roles.includes(user.role);
  }

  private saveUser(user: AuthResponse): void {
    localStorage.setItem(STORAGE_KEY, JSON.stringify(user));
    this.currentUser.set(user);
  }

  private loadUser(): AuthResponse | null {
    const saved = localStorage.getItem(STORAGE_KEY);
    return saved ? JSON.parse(saved) : null;
  }
}
