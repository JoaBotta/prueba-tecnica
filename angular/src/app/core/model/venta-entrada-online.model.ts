// venta-entrada-online.model.ts
export interface DetalleVentaEntrada {
    idEntrada: number;
    cantidad: number;
  }
  
  export interface VentaEntradaOnlineRequest {
    bolicheId: number;
    formaDePagoId: number;
    fechaHora: string;
    totalPrecio: number;
    nombreComprador: string;
    correoElectronico: string;
    telefono: string;
    detalleVentaEntrada: DetalleVentaEntrada[];
  }
  
  export interface DetalleVentaEntradaResponse {
    nombreEntrada: string;
    precioUnitario: number;
    cantidad: number;
    total: number;
  }
  
  export interface VentaEntradaOnlineResponse {
    id: number;
    formaDePagoId: number;
    fechaHora: string;
    totalPrecio: number;
    nombreComprador: string;
    correoElectronico: string;
    telefono: string;
    bolicheNombre: string;
    detalleVentaEntrada: DetalleVentaEntradaResponse[];
  }
  