import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Lista } from '@core/model/lista.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ListaResumen } from '@core/model/listaResumen.model';

@Injectable({ providedIn: 'root' })
export class ListaService {
  
    private apiUrl = `${environment.apiUrl}/api/listas`;

  constructor(private http: HttpClient) {}

  getListas(): Observable<Lista[]> {
    return this.http.get<Lista[]>(this.apiUrl);
  }

  getLista(id: number): Observable<Lista> {
    return this.http.get<Lista>(`${this.apiUrl}/${id}`);
  }

  crearLista(lista: Lista): Observable<Lista> {
    return this.http.post<Lista>(this.apiUrl, lista);
  }

  actualizarLista(id: number, lista: Lista): Observable<Lista> {
    return this.http.put<Lista>(`${this.apiUrl}/${id}`, lista);
  }

  eliminarLista(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getResumenListas(): Observable<ListaResumen[]> {
    return this.http.get<ListaResumen[]>(`${this.apiUrl}/resumen`);
  }
}