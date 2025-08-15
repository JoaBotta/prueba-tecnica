package com.joa.springboot.ListaEvento;

import java.time.LocalDateTime;

public class ListaResumenDTO {
    private Long id;
    private String nombre;
    private LocalDateTime fechaHora;
    private int cantidad;

    public ListaResumenDTO(Long id, String nombre, LocalDateTime fechaHora, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.fechaHora = fechaHora;
        this.cantidad = cantidad;
    }
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
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
