package com.joa.springboot.PuntoDeVenta;

import jakarta.persistence.*;
import com.joa.springboot.Boliche.Boliche;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.VentaEntrada.VentaEntrada;
import java.util.List;

@Entity
@Table(name = "punto_de_venta")
public class PuntoDeVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "boliche_id", nullable = false)
    private Boliche boliche;

    @ManyToOne
    @JoinColumn(name = "seguridad_id", nullable = false)
    private Usuario seguridad;

    @OneToMany(mappedBy = "puntoDeVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaEntrada> ventas;

    // Constructor vacío
    public PuntoDeVenta() {}

    // Constructor con parámetros
    public PuntoDeVenta(String nombre, Boliche boliche, Usuario seguridad) {
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

    public Boliche getBoliche() {
        return boliche;
    }

    public void setBoliche(Boliche boliche) {
        this.boliche = boliche;
    }

    public Usuario getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(Usuario seguridad) {
        this.seguridad = seguridad;
    }

    public List<VentaEntrada> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaEntrada> ventas) {
        this.ventas = ventas;
    }
}
