import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PuntoDeVenta, PuntoDeVentaRequest } from '../model/punto-de-venta.model';

@Injectable({
  providedIn: 'root',
})
export class PuntoDeVentaService {
  private apiUrl = `${environment.apiUrl}/api/puntos-de-venta`;

  constructor(private http: HttpClient) {}

  // Obtener todos los puntos de venta de un boliche
  getPuntosDeVenta(bolicheId: number): Observable<PuntoDeVenta[]> {
    return this.http.get<PuntoDeVenta[]>(`${this.apiUrl}/boliche/${bolicheId}`);
  }

  // Crear un nuevo punto de venta
  createPuntoDeVenta(puntoDeVenta: PuntoDeVentaRequest): Observable<PuntoDeVenta> {
    return this.http.post<PuntoDeVenta>(this.apiUrl, puntoDeVenta);
  }

  // Eliminar un punto de venta
  deletePuntoDeVenta(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // Obtener todos los puntos de venta de un boliche
  getPuntosDeVentaByBoliche(bolicheId: number): Observable<PuntoDeVenta[]> {
    return this.http.get<PuntoDeVenta[]>(`${this.apiUrl}/boliche/${bolicheId}`);
  }


}