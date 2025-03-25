package com.joa.springboot.EntradaGenerada;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;

import jakarta.persistence.*;

@Entity
public class EntradaGenerada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private DetalleVentaEntrada detalleVentaEntrada;

    private String qrCode;
    private Boolean usada = false;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleVentaEntrada getDetalleVentaEntrada() {
        return detalleVentaEntrada;
    }

    public void setDetalleVentaEntrada(DetalleVentaEntrada detalleVentaEntrada) {
        this.detalleVentaEntrada = detalleVentaEntrada;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Boolean getUsada() {
        return usada;
    }

    public void setUsada(Boolean usada) {
        this.usada = usada;
    }
}
