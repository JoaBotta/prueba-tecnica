// src/app/core/model/venta-barra.model.ts

import { Barra } from './barra.model';
import { DetalleVentaBarra } from './detalleVentaBarra.model';
import { Usuario } from './usuario/usuario.model';
import { FormaDePago } from './formaDePago.model';

export interface VentaBarra {
  id: number; // El ID será generado por el backend
  barra: Barra; // Relación con el modelo Barra
  detalleVenta: DetalleVentaBarra[]; // Relación con los detalles de venta
  formaDePago: FormaDePago; // Forma de pago seleccionada
  total: number; // Total de la venta
  fecha: Date; // Fecha de la venta, la manejaremos como tipo Date
}