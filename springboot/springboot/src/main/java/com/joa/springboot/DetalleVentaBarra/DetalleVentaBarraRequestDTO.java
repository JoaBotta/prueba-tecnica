package com.joa.springboot.DetalleVentaBarra;

public class DetalleVentaBarraRequestDTO {
    private Long productoId;
    private int cantidad;

    // Getters y Setters

    public Long getProductoId() {  // Cambiado a getProductoId
        return productoId;
    }

    public void setProductoId(Long productoId) {  // Cambiado a setProductoId
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
