package com.joa.springboot.producto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class productoResponseDTO {
    private Long id; // Identificador del producto
    private String nombre; // Nombre del producto
    private BigDecimal precioUnitario; // Precio por unidad
    private String descripcion; // Descripción del producto
    private Integer stock; // Stock disponible
}
