package com.joa.springboot.Entrada;

import java.math.BigDecimal;

public class EntradaResponseDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;

    // Constructor
    public EntradaResponseDTO(Long id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public BigDecimal getPrecio() { return precio; }
}
