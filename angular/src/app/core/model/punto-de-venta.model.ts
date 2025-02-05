export interface PuntoDeVenta {
    id: number;
    nombre: string;
    bolicheNombre: string;
    seguridadNombre: string;
  }
  
  export interface PuntoDeVentaRequest {
    nombre: string;
    seguridadNombre: string;
    bolicheId: number;
  }