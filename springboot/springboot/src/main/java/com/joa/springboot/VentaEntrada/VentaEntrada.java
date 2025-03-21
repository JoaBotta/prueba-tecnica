package com.joa.springboot.VentaEntrada;

import jakarta.persistence.*;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.FormaDePago.FormaDePago;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas_entrada_online")
public abstract class VentaEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private PuntoDeVenta puntoDeVenta;

    @ManyToOne(optional = false)
    private FormaDePago formaDePago;

    private LocalDateTime fechaHora;

    private Double totalPrecio;

    // Getters y setters

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