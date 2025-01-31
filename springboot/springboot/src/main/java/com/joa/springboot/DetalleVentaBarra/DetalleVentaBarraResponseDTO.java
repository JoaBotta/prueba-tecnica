package com.joa.springboot.DetalleVentaBarra;

import java.math.BigDecimal;

public class DetalleVentaBarraResponseDTO {
    private Long id;
    private String productoNombre;
    private int cantidad;
    private BigDecimal subTotal;

    // Constructor
    public DetalleVentaBarraResponseDTO(Long id, String productoNombre, int cantidad, BigDecimal subTotal) {
        this.id = id;
        this.productoNombre = productoNombre;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
