package com.joa.springboot.DetalleVentaEntrada;

import java.math.BigDecimal;

public class DetalleVentaEntradaResponseDTO {
    private Long id;
    private String entradaNombre;
    private String QR;
    private int cantidad;
    private BigDecimal subTotal;

    // Constructor
    public DetalleVentaEntradaResponseDTO(Long id, String entradaNombre, String QR, int cantidad, BigDecimal subTotal) {
        this.id = id;
        this.entradaNombre = entradaNombre;
        this.QR = QR;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getSubTotal() { return subTotal; }
    public void setSubTotal(BigDecimal subTotal) { this.subTotal = subTotal; }
    
    public String getQrCode() {
        return QR;
    }

    public void setQrCode(String QR) {
        this.QR = QR;
    }
    public String getEntradaoNombre() {
        return entradaNombre;
    }

    public void setEntradaNombre(String entradaNombre) {
        this.entradaNombre = entradaNombre;
    }
}
