package com.joa.springboot.DetalleVentaBarra;

public class DetalleVentaBarraResponseDTO {
    private Long id;
    private String productoNombre;
    private int cantidad;
    private double subTotal;

    // Constructor
    public DetalleVentaBarraResponseDTO(Long id, String productoNombre, int cantidad, double subTotal) {
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

    public String getproductoNombre() {
        return productoNombre;
    }

    public void setproductoNombre(String productoNombre) {
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

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
