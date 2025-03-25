package com.joa.springboot.VentaEntradaOnline;
import com.joa.springboot.VentaEntrada.VentaEntrada;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("ONLINE")
public class VentaEntradaOnline extends VentaEntrada {

    private String nombreComprador;
    private String correoElectronico;
    private String telefono;

    @OneToMany(mappedBy = "ventaEntradaOnline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaEntrada> detalleVentaEntrada;

    // Getters y Setters

    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<DetalleVentaEntrada> getDetalleVentaEntrada() {
        return detalleVentaEntrada;
    }

    public void setDetalleVentaEntrada(List<DetalleVentaEntrada> detalleVentaEntrada) {
        this.detalleVentaEntrada = detalleVentaEntrada;
    }
}