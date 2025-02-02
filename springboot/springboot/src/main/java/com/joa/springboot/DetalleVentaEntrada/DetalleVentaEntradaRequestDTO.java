package com.joa.springboot.DetalleVentaEntrada;

public class DetalleVentaEntradaRequestDTO {
    private Long ventaEntradaId;
    private int cantidad;
    private Long entradaId;

    // Getters y Setters
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Long getEntradaId() {
        return entradaId;
    }

    public void setEntradaId(Long entradaId) {
        this.entradaId = entradaId;
    }
    public Long getVentaEntradaId() {
        return ventaEntradaId;
    }

    public void setVentaEntradaId(Long ventaEntradaId) {
        this.ventaEntradaId = ventaEntradaId;
    }
}
