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

    @Column(nullable = false)
    private double subTotal;

    // Constructor por defecto
    public DetalleVentaBarra() {
    }

    // Constructor con parámetros
    public DetalleVentaBarra(VentaBarra ventaBarra, Producto producto, int cantidad) {
        this.ventaBarra = ventaBarra;
        this.producto = producto;
        this.cantidad = cantidad;
        this.subTotal = calcularSubTotal();
    }

    // Método para calcular el subtotal
    public double calcularSubTotal() {
        return producto.getPrecioUnitario().multiply(BigDecimal.valueOf(cantidad)).doubleValue();
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
        this.subTotal = calcularSubTotal(); // Recalcular subtotal si cambia el producto
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subTotal = calcularSubTotal(); // Recalcular subtotal si cambia la cantidad
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "DetalleVentaBarra{" +
                "id=" + id +
                ", ventaBarra=" + (ventaBarra != null ? ventaBarra.getId() : "null") +
                ", producto=" + (producto != null ? producto.getNombre() : "null") +
                ", cantidad=" + cantidad +
                ", subTotal=" + subTotal +
                '}';
    }
}
