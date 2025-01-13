package com.joa.springboot.FormaDePago;

public class FormaDePagoResponseDTO {
    private Long id;
    private String nombre;

    // Constructor
    public FormaDePagoResponseDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
