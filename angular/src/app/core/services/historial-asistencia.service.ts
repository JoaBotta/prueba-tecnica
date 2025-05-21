import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HistorialAsistencia } from '@core/model/historial-asistencia.model';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class HistorialAsistenciaService {
    
    private baseUrl = `${environment.apiUrl}/api/historial-asistencia`;

  constructor(private http: HttpClient) {}

  getByClienteId(clienteId: number): Observable<HistorialAsistencia[]> {
    return this.http.get<HistorialAsistencia[]>(`${this.baseUrl}/cliente/${clienteId}`);
  }
}
