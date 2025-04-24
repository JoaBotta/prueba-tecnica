import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cliente } from '@core/model/cliente.model';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ClienteService {

    private apiUrl = `${environment.apiUrl}/api/clientes`;

  constructor(private http: HttpClient) {}

  agregarCliente(listaId: number, cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.apiUrl}/lista/${listaId}`, cliente);
  }

  obtenerClientesPorLista(listaId: number): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.apiUrl}/lista/${listaId}`);
  }

  actualizarAsistencia(clienteId: number, asistencia: boolean): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${clienteId}/asistencia?asistencia=${asistencia}`, {});
  }
}