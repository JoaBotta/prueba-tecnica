package com.joa.springboot.VentaEntradaOnline;
import com.joa.springboot.EntradaOnline.EntradaOnline;
import com.joa.springboot.Evento.Evento;
import com.joa.springboot.FormaDePago.FormaDePago;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Venta_Entrada_Online")
public class VentaEntradaOnline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private FormaDePago formaDePago;
    private LocalDateTime fechaHora;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal Precio;

    private String nombreComprador;
    private String correoElectronico;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "boliche_id", nullable = false)
    private Evento boliche;

    @ManyToOne(optional = false)
    @JoinColumn(name = "entrada_online_id", nullable = false)  // ← muchas ventas de la misma entrada
    private EntradaOnline entradaOnline;


    // Getters y Setters
    public Evento getBoliche() {
        return boliche;
    }
    public void setBoliche(Evento boliche) {
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

    public BigDecimal getPrecio() {
        return Precio;
    }
    public void setPrecio(BigDecimal Precio) {
        this.Precio = Precio;
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

    public EntradaOnline getEntradaOnline() {
        return entradaOnline;
    }
    public void setEntradaOnline(EntradaOnline entradaOnline) {
        this.entradaOnline = entradaOnline;
    }

}