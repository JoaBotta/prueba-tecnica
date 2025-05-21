import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Estado } from '../model/estado.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class EstadoService {
  private apiUrl = `${environment.apiUrl}/api/estado`;



  constructor(private http: HttpClient) {}

  getAll(): Observable<Estado[]> {
    return this.http.get<Estado[]>(`${this.apiUrl}`);
  }
}
