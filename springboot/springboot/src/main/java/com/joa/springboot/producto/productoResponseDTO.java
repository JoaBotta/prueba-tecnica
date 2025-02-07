package com.joa.springboot.Producto;

import java.math.BigDecimal;

public class ProductoResponseDTO {
    private Long id;
    private String nombre;
    private BigDecimal precioUnitario;
    private String descripcion;

    // Constructor
    public ProductoResponseDTO(Long id, String nombre, BigDecimal precioUnitario, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.descripcion = descripcion;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
