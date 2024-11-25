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
    this.startLogoutTimer(); // Inicia el temporizador al guardar el token
  }

  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  logout(): void {
    localStorage.removeItem('authToken');
    this.clearLogoutTimer(); // Limpia el temporizador
    this.router.navigate(['/login']); // Redirige al login
  }

  // Inicia el temporizador para cerrar sesión automáticamente
  startLogoutTimer(): void {
    this.clearLogoutTimer(); // Limpia temporizadores anteriores si existen
    this.logoutTimer = setTimeout(() => {
      this.logout();
      alert('Tu sesión ha expirado por inactividad.');
    }, 15 * 60 * 1000); // 15 minutos en milisegundos
  }

  // Reinicia el temporizador en actividad del usuario
  resetLogoutTimer(): void {
    this.startLogoutTimer();
  }

  // Limpia el temporizador
  clearLogoutTimer(): void {
    if (this.logoutTimer) {
      clearTimeout(this.logoutTimer);
      this.logoutTimer = null;
    }
  }
}
