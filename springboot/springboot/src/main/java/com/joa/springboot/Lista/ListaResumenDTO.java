package com.joa.springboot.Lista;

import java.time.LocalDate;

public class ListaResumenDTO {
    private Long id;
    private String nombre;
    private LocalDate fecha;

    // Constructor
    public ListaResumenDTO(Long id, String nombre, LocalDate fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public LocalDate getFecha() { return fecha; }
}
