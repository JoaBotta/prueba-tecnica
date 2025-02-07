import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { LoginRequest } from '../model/auth/login-request.model';
import { RegisterRequest } from '../model/auth/register-request.model';
import { AuthResponse } from '../model/auth/auth-response.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.apiUrl}/auth`;
  private logoutTimer: any;

  constructor(private http: HttpClient, private router: Router) { }

  login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, request);
  }

  register(request: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, request);
  }

  saveToken(token: string): void {
    localStorage.setItem('authToken', token);
  }

  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

 /* isAuthenticated(): boolean {
    return !!this.getToken();
  }*/
  isAuthenticated(): boolean {
    const token = this.getToken();
    if (!token) return false;
  
    // Verificar si el token ha expirado
    const payload = JSON.parse(atob(token.split('.')[1])); // Decodificar el token
    const isExpired = payload.exp * 1000 < Date.now(); // Comparar con el tiempo actual
  
    return !isExpired;
  }

  logout(): void {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']); // Redirige al login
  }

  
}
