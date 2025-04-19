import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EntradaOnline } from '../model/entradaOnline.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EntradaOnlineService {
  
  private apiUrl = `${environment.apiUrl}/api/entradaOnline`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<EntradaOnline[]> {
    return this.http.get<EntradaOnline[]>(this.apiUrl);
  }

  getById(id: number): Observable<EntradaOnline> {
    return this.http.get<EntradaOnline>(`${this.apiUrl}/${id}`);
  }

  create(data: EntradaOnline): Observable<EntradaOnline> {
    return this.http.post<EntradaOnline>(this.apiUrl, data);
  }

  update(id: number, data: EntradaOnline): Observable<EntradaOnline> {
    return this.http.put<EntradaOnline>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
