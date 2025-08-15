package com.joa.springboot.DetalleVentaEntradaFisica;

public class DetalleVentaEntradaFisicaRequestDTO {
    private Long entradaFisicaId;
    private int cantidad;

    // Getters y Setters
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
