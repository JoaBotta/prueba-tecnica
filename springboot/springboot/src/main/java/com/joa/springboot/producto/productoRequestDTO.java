package com.joa.springboot.Producto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductoRequestDTO {
    private String nombre; // Nombre del producto
    private BigDecimal precioUnitario; // precioUnitario por unidad
    private String descripcion; // Descripción opcional
    private Integer stock; // Stock disponible
}
