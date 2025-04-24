import { Cliente } from './cliente.model';


export interface Lista {
  id?: number;
  nombre: string;
  fecha: string; // formato ISO string
  clientes?: Cliente[];
}
