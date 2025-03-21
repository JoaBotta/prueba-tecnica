package com.joa.springboot.QrEntrada;

import java.math.BigDecimal;

public class QrEntradaResponseDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;

    // Constructor
    public QrEntradaResponseDTO(Long id, String nombre, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public BigDecimal getPrecio() { return precio; }
}
