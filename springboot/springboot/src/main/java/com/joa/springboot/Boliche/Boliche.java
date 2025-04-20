package com.joa.springboot.Boliche;

import jakarta.persistence.*;
import com.joa.springboot.Servicios.Servicio;
import com.joa.springboot.PuntoDeVenta.PuntoDeVenta;
import com.joa.springboot.VentaEntradaOnline.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.joa.springboot.Barra.Barra;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boliches")
public class Boliche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String provincia;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String calle;

    @Column(nullable = false)
    private int capacidadMaxima;

    @OneToMany(mappedBy = "boliche", cascade = CascadeType.ALL) // Eliminado orphanRemoval = true
    @JsonIgnore
    private List<Servicio> servicios = new ArrayList<>(); 

    @OneToMany(mappedBy = "boliche", cascade = CascadeType.ALL) // Eliminado orphanRemoval = true
    @JsonManagedReference
    private List<Barra> barras = new ArrayList<>();

    @OneToMany(mappedBy = "boliche", cascade = CascadeType.ALL) // Eliminado orphanRemoval = true
    @JsonManagedReference
    private List<PuntoDeVenta> puntoventa = new ArrayList<>();

    @OneToMany(mappedBy = "boliche", cascade = CascadeType.ALL) // Eliminado orphanRemoval = true
    @JsonManagedReference
    private List<VentaEntradaOnline> ventaentradaonline = new ArrayList<>();

    // Constructor por defecto
    public Boliche() {
        this.servicios = new ArrayList<>();
        this.barras = new ArrayList<>();
        this.puntoventa = new ArrayList<>();
    }

    // Constructor con parámetros
    public Boliche(String nombre, String provincia, String ciudad, String calle, int capacidadMaxima) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.capacidadMaxima = capacidadMaxima;
        this.servicios = new ArrayList<>();
        this.barras = new ArrayList<>();
        this.puntoventa = new ArrayList<>();
        this.ventaentradaonline = new ArrayList<>();
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Barra> getBarras() {
        return barras;
    }

    public void setBarras(List<Barra> barras) {
        this.barras = barras;
    }

    public List<PuntoDeVenta> getPuntoventa() {
        return puntoventa;
    }

    public void setPuntoventa(List<PuntoDeVenta> puntoventa) {
        this.puntoventa = puntoventa;
    }
    
    public List<VentaEntradaOnline> getVentaEntradaOnline() {
        return ventaentradaonline;
    }

    public void setVentaEntradaOnline(List<VentaEntradaOnline> ventaentradaonline) {
        this.ventaentradaonline = ventaentradaonline;
    }
}
