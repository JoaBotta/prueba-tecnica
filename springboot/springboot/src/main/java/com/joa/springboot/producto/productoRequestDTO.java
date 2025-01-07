package com.joa.springboot.producto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class productoRequestDTO {
    private String nombre; // Nombre del producto
    private BigDecimal precioUnitario; // Precio por unidad
    private String descripcion; // Descripción opcional
    private Integer stock; // Stock disponible
}
