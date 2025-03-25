import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Entrada, EntradaRequest } from '@core/model/entrada.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EntradaService {
  private apiUrl = `${environment.apiUrl}/api/entrada`;

  constructor(private http: HttpClient) {}

  getEntradas(): Observable<Entrada[]> {
    return this.http.get<Entrada[]>(this.apiUrl);
  }

  createEntrada(entrada: EntradaRequest): Observable<Entrada> {
    return this.http.post<Entrada>(this.apiUrl, entrada);
  }

  deleteEntrada(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
