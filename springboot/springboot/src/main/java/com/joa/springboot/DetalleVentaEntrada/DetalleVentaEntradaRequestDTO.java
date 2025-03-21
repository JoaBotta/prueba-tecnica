package com.joa.springboot.DetalleVentaEntrada;

public class DetalleVentaEntradaRequestDTO {
    private Long entradaId;
    private int cantidad;

    public Long getEntradaId() { return entradaId; }
    public void setEntradaId(Long entradaId) { this.entradaId = entradaId; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}