package com.joa.springboot.DetalleVentaFisica;

import jakarta.persistence.*;
import com.joa.springboot.Entrada.Entrada;
import com.joa.springboot.VentaEntradaFisica.VentaEntradaFisica;

@Entity
@Table(name = "detalle_venta_fisica")
public class DetalleVentaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private VentaEntradaFisica ventaEntradaFisica;

    @ManyToOne(optional = false)
    private Entrada entradaFisicaId;

    private int cantidad;
    private Double subtotal;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VentaEntradaFisica getVentaEntradaFisica() {
        return ventaEntradaFisica;
    }

    public void setVentaEntradaFisica(VentaEntradaFisica ventaEntradaFisica) {
        this.ventaEntradaFisica = ventaEntradaFisica;
    }

    public Entrada getEntradaFisicaId() {
        return entradaFisicaId;
    }

    public void setEntradaFisicaId(Entrada entradaFisicaId) {
        this.entradaFisicaId = entradaFisicaId;
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