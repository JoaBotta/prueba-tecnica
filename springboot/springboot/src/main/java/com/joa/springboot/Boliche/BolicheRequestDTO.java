package com.joa.springboot.Boliche;
import java.util.ArrayList;
import java.util.List;

public class BolicheRequestDTO {
    private String nombre;
    private String provincia;
    private String ciudad;
    private String calle;
    private int capacidadMaxima;
    private List<Long> barraIds = new ArrayList<>(); // Inicializado como vacío
    private List<Long> servicioIds = new ArrayList<>(); // Inicializado como vacío

    // Getters y Setters
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

    public List<Long> getBarraIds() {
        return barraIds;
    }

    public void setBarraIds(List<Long> barraIds) {
        this.barraIds = barraIds;
    }

    public List<Long> getServicioIds() {
        return servicioIds;
    }

    public void setServicioIds(List<Long> servicioIds) {
        this.servicioIds = servicioIds;
    }
}
