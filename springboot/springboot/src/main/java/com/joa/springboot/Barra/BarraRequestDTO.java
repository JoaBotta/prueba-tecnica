package com.joa.springboot.Barra;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200/")
public class BarraRequestDTO {
    private String nombre;
    private Long bolicheId; // ID del boliche asociado

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Long getBolicheId() {
        return bolicheId;
    }
    public void setBolicheId(Long bolicheId) {
        this.bolicheId = bolicheId;
    }
}
