package com.joa.springboot.VentaEntradaFisica;

import com.joa.springboot.VentaEntrada.VentaEntrada;
import com.joa.springboot.DetalleVentaFisica.DetalleVentaFisica;
import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("FISICA")
public class VentaEntradaFisica extends VentaEntrada {

    @OneToMany(mappedBy = "ventaEntradaFisica", cascade = CascadeType.ALL)
    private List<DetalleVentaFisica> detalles;

    // Getters y Setters

    public List<DetalleVentaFisica> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaFisica> detalles) {
        this.detalles = detalles;
    }
}
