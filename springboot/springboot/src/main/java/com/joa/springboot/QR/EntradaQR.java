package com.joa.springboot.QR;

import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import jakarta.persistence.*;

@Entity
public class EntradaQR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private DetalleVentaEntrada detalleVentaEntrada;

    private String textoQR; // Contenido codificado del QR (puede ser UUID o token)

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

    public String getTextoQR() {
        return textoQR;
    }

    public void setTextoQR(String textoQR) {
        this.textoQR = textoQR;
    }
}
