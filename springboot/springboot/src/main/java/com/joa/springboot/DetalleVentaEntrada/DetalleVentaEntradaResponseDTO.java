package com.joa.springboot.DetalleVentaEntrada;

import java.util.List;

public class DetalleVentaEntradaResponseDTO {

    private Long id;
    private Long entradaId;
    private int cantidad;
    private Double subtotal;
    private List<String> codigosQR;

    // 🔹 Constructor vacío (necesario para frameworks como Jackson)
    public DetalleVentaEntradaResponseDTO() {
    }

    // 🔹 Constructor que falta
    public DetalleVentaEntradaResponseDTO(Long id, Long entradaId, int cantidad, Double subtotal) {
        this.id = id;
        this.entradaId = entradaId;
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
    public List<String> getCodigosQR() {
        return codigosQR;
    }
    public void setCodigosQR(List<String> codigosQR) {
        this.codigosQR = codigosQR;
    }
}