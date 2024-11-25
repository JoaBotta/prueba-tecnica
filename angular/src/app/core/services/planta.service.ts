import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Planta } from '../model/planta.model';

@Injectable({
  providedIn: 'root',
})
export class PlantaService {
  private apiUrl = `${environment.apiUrl}/api/planta`;

  constructor(private http: HttpClient) {}

  getPlantas(): Observable<Planta[]> {
    return this.http.get<Planta[]>(`${this.apiUrl}`);
  }

  createPlanta(planta: Planta): Observable<Planta> {
    return this.http.post<Planta>(`${this.apiUrl}`, planta);
  }

  updatePlanta(id: number, planta: Planta): Observable<Planta> {
    return this.http.put<Planta>(`${this.apiUrl}/${id}`, planta);
  }

  deletePlanta(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  getLecturasOk(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/lecturas-ok`);
  }

  getAlertasMedias(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/alertas-medias`);
  }

  getAlertasRojas(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/alertas-rojas`);
  }

  getSensoresDeshabilitados(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/sensores-deshabilitados`);
  }

}
