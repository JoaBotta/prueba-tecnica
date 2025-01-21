package com.joa.springboot.VentaBarra;

public class TicketDetalleDTO {
    private String productoNombre;
    private int cantidad;
    private double subTotal;

    // Constructor
    public TicketDetalleDTO(String productoNombre, int cantidad, double subTotal) {
        this.productoNombre = productoNombre;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    // Getters y Setters

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

    public double getSubTotal() {
        return subTotal;
    }

    
    // (Generar automáticamente según tu IDE)
}
