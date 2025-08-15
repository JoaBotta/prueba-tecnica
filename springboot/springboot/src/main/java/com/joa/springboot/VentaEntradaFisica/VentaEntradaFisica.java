package com.joa.springboot.VentaEntradaFisica;

import jakarta.persistence.*;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.DetalleVentaEntradaFisica.DetalleVentaEntradaFisica;
import com.joa.springboot.FormaDePago.FormaDePago;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Venta_Entrada_Fisica")
public class VentaEntradaFisica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private PuntoDeVenta puntoDeVenta;

    @ManyToOne(optional = false)
    private FormaDePago formaDePago;

    private LocalDateTime fechaHora;

    private Double totalPrecio;

    @OneToMany(mappedBy = "ventaEntrada", cascade = CascadeType.ALL)
    private List<DetalleVentaEntradaFisica> detalles;

    // Getters y setters
    
    public List<DetalleVentaEntradaFisica> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaEntradaFisica> detalles) {
        this.detalles = detalles;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PuntoDeVenta getPuntoDeVenta() {
        return puntoDeVenta;
    }

    public void setPuntoDeVenta(PuntoDeVenta puntoDeVenta) {
        this.puntoDeVenta = puntoDeVenta;
    }

    public FormaDePago getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaDePago formaDePago) {
        this.formaDePago = formaDePago;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(Double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }
}