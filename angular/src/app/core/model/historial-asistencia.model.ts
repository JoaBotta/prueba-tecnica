import { Estado } from './estado.model';

export interface HistorialAsistencia {
  id: number;
  clienteId: number;
  estado: Estado;
  fechaHora: string; // ISO 8601 format (e.g., "2025-04-30T18:00:00")
}
