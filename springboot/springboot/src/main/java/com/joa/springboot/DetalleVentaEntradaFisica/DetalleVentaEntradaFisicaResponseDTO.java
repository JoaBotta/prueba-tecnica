package com.joa.springboot.DetalleVentaEntradaFisica;

public class DetalleVentaEntradaFisicaResponseDTO {
    private Long id;
    private Long entradaFisicaId;
    private int cantidad;
    private Double subtotal;

    // Constructor vacío
    public DetalleVentaEntradaFisicaResponseDTO() {
    }

    // Constructor con parámetros
    public DetalleVentaEntradaFisicaResponseDTO(Long id, Long entradaFisicaId, int cantidad, Double subtotal) {
        this.id = id;
        this.entradaFisicaId = entradaFisicaId;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntradaFisicaId() {
        return entradaFisicaId;
    }

    public void setEntradaFisicaId(Long VentaEntradaId) {
        this.entradaFisicaId = VentaEntradaId;
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
