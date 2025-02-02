package com.joa.springboot.Entrada;

import java.math.BigDecimal;

public class EntradaRequestDTO {
    private String nombre;
    private BigDecimal precio;

    // Getters y Setters
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
}
