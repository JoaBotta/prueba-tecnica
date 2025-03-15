package com.joa.springboot.DetalleVentaEntrada;

public class DetalleVentaEntradaRequestDTO {
    private Long entradaId;   // Puede ser null
    private Long qrEntradaId; // Puede ser null
    private int cantidad;

    public Long getEntradaId() { return entradaId; }
    public void setEntradaId(Long entradaId) { this.entradaId = entradaId; }

    public Long getQrEntradaId() { return qrEntradaId; }
    public void setQrEntradaId(Long qrEntradaId) { this.qrEntradaId = qrEntradaId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
