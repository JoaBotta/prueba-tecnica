import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class DetalleVentaBarraService {
  private apiUrl = `${environment.apiUrl}/api/detalles-venta-barra`;

  constructor(private http: HttpClient) {}

  crearDetalleVentaBarra(detalle: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, detalle);
  }

  listarDetallesVentaBarra(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  eliminarDetalleVentaBarra(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
