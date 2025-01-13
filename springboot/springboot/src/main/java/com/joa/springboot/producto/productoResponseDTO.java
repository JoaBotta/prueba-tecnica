package com.joa.springboot.Producto;

import lombok.Data;

import java.math.BigDecimal;
//
@Data
public class ProductoResponseDTO {
    private Long id; // Identificador del producto
    private String nombre; // Nombre del producto
    private BigDecimal precioUnitario; // precioUnitario por unidad
    private String descripcion; // Descripción del producto
    private Integer stock; // Stock disponible
}
