package com.joa.springboot.DetalleVentaEntradaFisica;

import jakarta.persistence.*;

import com.joa.springboot.EntradaFisica.EntradaFisica;
import com.joa.springboot.VentaEntradaFisica.VentaEntradaFisica;

@Entity
@Table(name = "detalle_venta_fisica")
public class DetalleVentaEntradaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private VentaEntradaFisica ventaEntrada;

    @ManyToOne(optional = false)
    private EntradaFisica entradaId;

    private int cantidad;
    private Double subtotal;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VentaEntradaFisica getVentaEntrada() {
        return ventaEntrada;
    }

    public void setVentaEntrada(VentaEntradaFisica ventaEntrada) {
        this.ventaEntrada = ventaEntrada;
    }

    public EntradaFisica getEntradaFisicaId() {
        return entradaId;
    }

    public void setEntradaFisicaId(EntradaFisica entradaId) {
        this.entradaId = entradaId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}