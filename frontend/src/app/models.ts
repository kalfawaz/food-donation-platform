export type Role = 'DONOR' | 'CHARITY' | 'VOLUNTEER' | 'ADMIN';

export interface AuthResponse {
  token: string;
  name: string;
  email: string;
  role: Role;
}

export interface Donation {
  donationId?: number;
  foodType: string;
  category: string;
  quantity: number | null;
  preparedAt: string;
  expiresAt: string;
  pickupLocation: string;
  description?: string;
  status?: string;
  donorName?: string;
  createdAt?: string;
}
