package com.joa.springboot.VentaEntradaOnline;
import com.joa.springboot.DetalleVentaEntrada.DetalleVentaEntrada;
import com.joa.springboot.FormaDePago.FormaDePago;
import com.joa.springboot.Boliche.Boliche;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@DiscriminatorValue("ONLINE")
public class VentaEntradaOnline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private FormaDePago formaDePago;

    private LocalDateTime fechaHora;

    private Double totalPrecio;

    private String nombreComprador;
    private String correoElectronico;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "boliche_id", nullable = false)
    private Boliche boliche;

    @OneToMany(mappedBy = "ventaEntradaOnline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaEntrada> detalleVentaEntrada;

    // Getters y Setters
    public Boliche getBoliche() {
        return boliche;
    }

    public void setBoliche(Boliche boliche) {
        this.boliche = boliche;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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