package com.joa.springboot.Servicios;

import java.math.BigDecimal;

public class ServicioRequestDTO {
    private String nombre;
    private String descripcion;
    private Long bolicheId; // ID del boliche asociado
    private BigDecimal precio; // Precio del servicio

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getBolicheId() {
        return bolicheId;
    }

    public void setBolicheId(Long bolicheId) {
        this.bolicheId = bolicheId;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
