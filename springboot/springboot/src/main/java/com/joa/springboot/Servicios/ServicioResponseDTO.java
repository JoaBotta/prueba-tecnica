package com.joa.springboot.Servicios;

import java.math.BigDecimal;

public class ServicioResponseDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private String descripcion;
    private String bolicheNombre;

    // ✅ Constructor
    public ServicioResponseDTO(Long id, String nombre, BigDecimal precio, String descripcion, String bolicheNombre) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.bolicheNombre = bolicheNombre;
    }

    // ✅ Getters y Setters
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBolicheNombre() {
        return bolicheNombre;
    }

    public void setBolicheNombre(String bolicheNombre) {
        this.bolicheNombre = bolicheNombre;
    }
}
