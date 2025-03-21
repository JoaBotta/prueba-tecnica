package com.joa.springboot.DetalleVentaEntrada;

import java.math.BigDecimal;

public class DetalleVentaEntradaResponseDTO {
    private Long id;
    private String entradaNombre;
    private int cantidad;
    private BigDecimal subTotal;

    // 🔹 Constructor para entradas normales (sin QR)
    public DetalleVentaEntradaResponseDTO(Long id, String entradaNombre, int cantidad, BigDecimal subTotal) {
        this.id = id;
        this.entradaNombre = entradaNombre;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }
    // Getters
    public Long getId() { return id; }
    public String getEntradaNombre() { return entradaNombre; }
    public int getCantidad() { return cantidad; }
    public BigDecimal getSubTotal() { return subTotal; }
}