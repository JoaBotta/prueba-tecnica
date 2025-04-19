import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Entrada } from '../model/entrada.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EntradaService {
  private apiUrl = `${environment.apiUrl}/api/entrada`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Entrada[]> {
    return this.http.get<Entrada[]>(this.apiUrl);
  }

  getById(id: number): Observable<Entrada> {
    return this.http.get<Entrada>(`${this.apiUrl}/${id}`);
  }

  create(entrada: Entrada): Observable<Entrada> {
    return this.http.post<Entrada>(this.apiUrl, entrada);
  }

  update(id: number, entrada: Entrada): Observable<Entrada> {
    return this.http.put<Entrada>(`${this.apiUrl}/${id}`, entrada);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
