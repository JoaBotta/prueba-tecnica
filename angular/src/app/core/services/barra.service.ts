import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Barra } from '../model/barra.model';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
  })
  export class BarraService {
    private apiUrl = `${environment.apiUrl}/api/barra`;
  
    constructor(private http: HttpClient) {}
  
    getBarras(): Observable<Barra[]> {
      return this.http.get<Barra[]>(this.apiUrl);
    }
  
    createBarra(barra: { nombre: string; bolicheId: number }): Observable<Barra> {
      return this.http.post<Barra>(this.apiUrl, barra);
    }

    getBarrasByBoliche(bolicheId: number): Observable<any[]> {
      return this.http.get<any[]>(`${this.apiUrl}/boliches/${bolicheId}/barras`);
  }
  }