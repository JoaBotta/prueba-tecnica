package com.joa.springboot.DetalleVentaQrEntrada;

public class DetalleVentaQrEntradaRequestDTO {
    private Long qrEntradaId;
    private Long ventaEntradaId;
    private int cantidad;

    public Long getQrEntradaId() { return qrEntradaId; }
    public void setQrEntradaId(Long qrEntradaId) { this.qrEntradaId = qrEntradaId; }

    public Long getVentaEntradaId() { return ventaEntradaId; }
    public void setVentaEntradaId(Long ventaEntradaId) { this.ventaEntradaId = ventaEntradaId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
