export interface Cliente {
  id?: number;
  nombre: string;
  apellido: string;
  documento: string;
  telefono: string;
  email: string;
  estadoId: number; // solo para enviar
  estadoNombre?: string; // solo para recibir
}
