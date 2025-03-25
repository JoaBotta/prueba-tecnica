package com.joa.springboot.DetalleVentaFisica;

public class DetalleVentaFisicaResponseDTO {
    private Long id;
    private Long entradaFisicaId;
    private int cantidad;
    private Double subtotal;

    // Constructor vacío
    public DetalleVentaFisicaResponseDTO() {
    }

    // Constructor con parámetros
    public DetalleVentaFisicaResponseDTO(Long id, Long entradaFisicaId, int cantidad, Double subtotal) {
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

    public void setEntradaFisicaId(Long ventaEntradaFisicaId) {
        this.entradaFisicaId = ventaEntradaFisicaId;
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
