import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HistorialAsistencia } from '@core/model/historial-asistencia.model';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AsistenciaService {
  
  private apiUrl = `${environment.apiUrl}/api/historial-asistencia`;

  constructor(private http: HttpClient) {}

  getHistoriales(): Observable<HistorialAsistencia[]> {
    return this.http.get<HistorialAsistencia[]>(this.apiUrl);
  }

  getHistorial(id: number): Observable<HistorialAsistencia> {
    return this.http.get<HistorialAsistencia>(`${this.apiUrl}/${id}`);
  }

  crearHistorial(historial: HistorialAsistencia): Observable<HistorialAsistencia> {
    return this.http.post<HistorialAsistencia>(this.apiUrl, historial);
  }

  eliminarHistorial(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
