package com.joa.springboot.DetalleVentaBarra;

public class DetalleVentaBarraRequestDTO {
    private Long productoId;
    private int cantidad;

    // Getters y Setters
    public Long getproductoId() {
        return productoId;
    }

    public void setproductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
