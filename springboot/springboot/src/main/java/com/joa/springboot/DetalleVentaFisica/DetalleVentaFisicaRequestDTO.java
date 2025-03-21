package com.joa.springboot.DetalleVentaFisica;

public class DetalleVentaFisicaRequestDTO {
    private Long ventaEntradaFisicaId;
    private Long entradaFisicaId;
    private int cantidad;

    // Getters y Setters
    public Long getVentaEntradaFisicaId() {
        return ventaEntradaFisicaId;
    }

    public void setVentaEntradaFisicaId(Long ventaEntradaFisicaId) {
        this.ventaEntradaFisicaId = ventaEntradaFisicaId;
    }

    public Long getEntradaFisicaId() {
        return entradaFisicaId;
    }

    public void setEntradaFisicaId(Long entradaId) {
        this.entradaFisicaId = entradaId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
