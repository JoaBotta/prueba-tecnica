package com.joa.springboot.Boliche;

import java.util.List;

public class BolicheResponseDTO {

    private Long id;
    private String nombre;
    private String provincia;
    private String ciudad;
    private String calle;
    private int capacidadMaxima;

    // Constructor
    public BolicheResponseDTO(Long id, String nombre, String provincia, String ciudad, String calle, int capacidadMaxima, String servicioNombre) {
        this.id = id;
        this.nombre = nombre;
        this.provincia = provincia;
        this.ciudad = ciudad;
        this.calle = calle;
        this.capacidadMaxima = capacidadMaxima;
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
}
