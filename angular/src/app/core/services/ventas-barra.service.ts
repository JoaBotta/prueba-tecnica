import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VentaBarra } from '../model/ventaBarra.model';
import { environment } from 'src/environments/environment';
import { DetalleVentaBarra } from '@core/model/detalleVentaBarra.model';

@Injectable({
  providedIn: 'root',
})
export class VentaBarraService {


  private apiUrl = `${environment.apiUrl}/api/ventas-barra`;
  constructor(private http: HttpClient) {}

  // Obtener todas las ventas
  getAllVentas(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);
  }

  
  // Crear una nueva venta
  createVenta(venta: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}`, venta);
  }

  getVentasByBarra(barraId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${barraId}`);
  }
}
