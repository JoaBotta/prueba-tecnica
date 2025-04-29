package com.joa.springboot.DetalleVentaFisica;

import jakarta.persistence.*;
import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.VentaEntrada.VentaEntrada;

@Entity
@Table(name = "detalle_venta_fisica")
public class DetalleVentaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private VentaEntrada ventaEntrada;

    @ManyToOne(optional = false)
    private Entrada entradaId;

    private int cantidad;
    private Double subtotal;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VentaEntrada getVentaEntrada() {
        return ventaEntrada;
    }

    public void setVentaEntrada(VentaEntrada ventaEntrada) {
        this.ventaEntrada = ventaEntrada;
    }

    public Entrada getEntradaFisicaId() {
        return entradaId;
    }

    public void setEntradaFisicaId(Entrada entradaId) {
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