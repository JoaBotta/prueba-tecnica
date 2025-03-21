package com.joa.springboot.DetalleVentaEntrada;

import lombok.Data;

@Data
public class DetalleVentaEntradaRequestDTO {

    private Long entradaId;
    private int cantidad;
    private Double subtotal;

    // Getters y Setters
    public Long getEntradaId() {
        return entradaId;
    }

    public void setEntradaId(Long entradaId) {
        this.entradaId = entradaId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}