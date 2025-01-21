import { Producto } from './producto.model';
import { VentaBarra } from './ventaBarra.model';

export interface DetalleVentaBarra {
  id?: number;
  ventaBarra: VentaBarra;
  producto: Producto;
  cantidad: number;
  subTotal: number;

}