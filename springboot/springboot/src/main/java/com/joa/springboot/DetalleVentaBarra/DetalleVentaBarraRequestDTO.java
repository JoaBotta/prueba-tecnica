package com.joa.springboot.DetalleVentaBarra;

public class DetalleVentaBarraRequestDTO {
    private Long productoId;
    private int cantidad;

    // Getters y Setters

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
