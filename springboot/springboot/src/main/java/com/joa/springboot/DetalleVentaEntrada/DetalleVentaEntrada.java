package com.joa.springboot.DetalleVentaEntrada;

import jakarta.persistence.*;
import java.util.List;
import com.joa.springboot.EntradaGenerada.EntradaGenerada;
import com.joa.springboot.EntradaOnline.EntradaOnline;
import com.joa.springboot.VentaEntradaOnline.VentaEntradaOnline;

@Entity
@Table(name = "detalle_venta_online")
public class DetalleVentaEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private VentaEntradaOnline ventaEntradaOnline;

    @ManyToOne(optional = false)
    private EntradaOnline entradaOnline;

    private int cantidad;
    private Double subtotal;

    @OneToMany(mappedBy = "detalleVentaEntrada", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntradaGenerada> entradasGeneradas;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VentaEntradaOnline getVentaEntradaOnline() {
        return ventaEntradaOnline;
    }

    public void setVentaEntradaOnline(VentaEntradaOnline ventaEntradaOnline) {
        this.ventaEntradaOnline = ventaEntradaOnline;
    }

    public EntradaOnline getEntradaOnline() {
        return entradaOnline;
    }

    public void setEntradaOnline(EntradaOnline entradaOnline) {
        this.entradaOnline = entradaOnline;
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

    public List<EntradaGenerada> getEntradasGeneradas() {
        return entradasGeneradas;
    }

    public void setEntradasGeneradas(List<EntradaGenerada> entradasGeneradas) {
        this.entradasGeneradas = entradasGeneradas;
    }
}