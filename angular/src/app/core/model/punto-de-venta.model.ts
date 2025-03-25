export interface PuntoDeVenta {
  id: number;
  nombre: string;
  bolicheNombre: string;
  seguridadNombre: string; // ✅ El backend devuelve el nombre o email del usuario
}

export interface PuntoDeVentaRequest {
  nombre: string;
  seguridadId: number; // ✅ Usar seguridadId para enviar el request
  bolicheId: number;
}
