export interface Cliente {
    id?: number;
    nombre: string;
    apellido: string;
    documento?: string;
    telefono?: string;
    asistencia?: boolean; // para indicar si estuvo presente hoy
  }
  