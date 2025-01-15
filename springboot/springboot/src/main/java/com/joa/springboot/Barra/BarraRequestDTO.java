package com.joa.springboot.Barra;

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
