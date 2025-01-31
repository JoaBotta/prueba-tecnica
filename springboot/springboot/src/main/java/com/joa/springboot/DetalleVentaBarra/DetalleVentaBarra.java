package com.joa.springboot.DetalleVentaBarra;

import jakarta.persistence.*;
import java.math.BigDecimal;

import com.joa.springboot.Producto.Producto;
import com.joa.springboot.VentaBarra.VentaBarra;

@Entity
@Table(name = "detalle_ventas_barra")
public class DetalleVentaBarra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "venta_barra_id", nullable = false)
    private VentaBarra ventaBarra;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subTotal;

    // Constructor por defecto
    public DetalleVentaBarra() {}

    // Constructor con parámetros
    public DetalleVentaBarra(VentaBarra ventaBarra, Producto producto, int cantidad) {
        this.ventaBarra = ventaBarra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subTotal = calcularSubTotal();
    }

    // Método para calcular el subtotal
    private BigDecimal calcularSubTotal() {
        if (producto != null && producto.getPrecioUnitario() != null) {
            return producto.getPrecioUnitario().multiply(BigDecimal.valueOf(cantidad));
        }
        return BigDecimal.ZERO;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VentaBarra getVentaBarra() {
        return ventaBarra;
    }

    public void setVentaBarra(VentaBarra ventaBarra) {
        this.ventaBarra = ventaBarra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        this.subTotal = calcularSubTotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subTotal = calcularSubTotal();
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
}
