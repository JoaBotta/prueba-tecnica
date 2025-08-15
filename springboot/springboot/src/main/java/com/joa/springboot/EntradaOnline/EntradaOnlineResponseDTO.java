package com.joa.springboot.EntradaOnline;

import java.math.BigDecimal;

public class EntradaOnlineResponseDTO {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private int stock;

    // Constructor
    public EntradaOnlineResponseDTO(Long id, String nombre, BigDecimal precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public BigDecimal getPrecio() { return precio; }
    public int getStock() { return stock; }
}
