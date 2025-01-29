package com.joa.springboot.Evento;

import jakarta.persistence.*;
import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.Servicios.Servicio;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private LocalDateTime fechaDesde;

    @Column(nullable = false)
    private LocalDateTime fechaHasta;

    @ManyToOne
    @JoinColumn(name = "boliche_id", nullable = false)
    private Boliche boliche;

    @ManyToMany
    @JoinTable(
        name = "evento_servicios",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    private List<Servicio> servicios;

    // Constructor vacío
    public Evento() {}

    // Constructor con parámetros
    public Evento(String nombre, LocalDateTime fechaDesde, LocalDateTime fechaHasta, Boliche boliche) {
        this.nombre = nombre;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.boliche = boliche;
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

    public LocalDateTime getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDateTime fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDateTime getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDateTime fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Boliche getBoliche() {
        return boliche;
    }

    public void setBoliche(Boliche boliche) {
        this.boliche = boliche;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
