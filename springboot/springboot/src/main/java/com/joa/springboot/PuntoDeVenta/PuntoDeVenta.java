package com.joa.springboot.PuntoDeVenta;

import jakarta.persistence.*;

import com.joa.springboot.Evento.Evento;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.VentaEntradaFisica.VentaEntradaFisica;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

@Entity
@CrossOrigin(origins = "http://localhost:4200")
@Table(name = "punto_de_venta")
public class PuntoDeVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "boliche_id", nullable = false)
    private Evento boliche;

    @ManyToOne
    @JoinColumn(name = "seguridad_id", nullable = false)
    private Usuario seguridad;

    @OneToMany(mappedBy = "puntoDeVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaEntradaFisica> ventaFisica;

    // Constructor vacío
    public PuntoDeVenta() {}

    // Constructor con parámetros
    public PuntoDeVenta(String nombre, Evento boliche, Usuario seguridad) {
        this.nombre = nombre;
        this.boliche = boliche;
        this.seguridad = seguridad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Evento getBoliche() {
        return boliche;
    }

    public void setBoliche(Evento boliche) {
        this.boliche = boliche;
    }

    public Usuario getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Usuario seguridad) {
        this.seguridad = seguridad;
    }

    public List<VentaEntradaFisica> getVentas() {
        return ventaFisica;
    }

    public void setVentas(List<VentaEntradaFisica> ventaFisica) {
        this.ventaFisica = ventaFisica;
    }
}
