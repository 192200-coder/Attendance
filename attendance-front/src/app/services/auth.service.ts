import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
// Interfaces basadas en tu backend
export interface AuthResponse {
  accessToken: string;
  refreshToken: string;
}
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/auth'; // Ajusta a tu puerto
  // Login
  login(credentials: any): Observable<AuthResponse> {
    return this.http
      .post<AuthResponse>(`${this.apiUrl}/login`, credentials)
      .pipe(tap((response) => this.saveTokens(response)));
  }

  getRefreshToken(): string | null {
    return localStorage.getItem('refresh_token');
  }

  // Refresh Token Call
  refreshToken(): Observable<AuthResponse> {
    const refreshToken = localStorage.getItem('refresh_token');
    return this.http
      .post<AuthResponse>(`${this.apiUrl}/refresh`, {
        refreshToken,
      })
      .pipe(tap((response) => this.saveTokens(response)));
  }

  // Utilidades
  private saveTokens(tokens: AuthResponse) {
    if (tokens.accessToken) {
      localStorage.setItem('access_token', tokens.accessToken);
    }
    if (tokens.refreshToken) {
      localStorage.setItem('refresh_token', tokens.refreshToken);
    }
  }

  getToken(): string | null {
    return localStorage.getItem('access_token');
  }

  logout() {
    localStorage.clear();
    window.location.href = '/login'; // O usar Router
  }
}
