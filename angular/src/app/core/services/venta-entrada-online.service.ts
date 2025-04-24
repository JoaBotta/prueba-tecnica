// venta-entrada-online.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { VentaEntradaOnlineRequest, VentaEntradaOnlineResponse } from '../model/venta-entrada-online.model';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VentaEntradaOnlineService {
  private apiUrl = `${environment.apiUrl}/api/venta-entrada/online`;

  constructor(private http: HttpClient) {}

  crearVenta(dto: VentaEntradaOnlineRequest): Observable<VentaEntradaOnlineResponse> {
    return this.http.post<VentaEntradaOnlineResponse>(this.apiUrl, dto);
  }

  obtenerTodas(): Observable<VentaEntradaOnlineResponse[]> {
    return this.http.get<VentaEntradaOnlineResponse[]>(this.apiUrl);
  }

  obtenerPorId(id: number): Observable<VentaEntradaOnlineResponse> {
    return this.http.get<VentaEntradaOnlineResponse>(`${this.apiUrl}/${id}`);
  }
}
