package com.joa.springboot.producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "producto")
public class producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; // Nombre del producto, e.g., "Cerveza", "Whisky"

    @Column(nullable = false)
    private BigDecimal precioUnitario; // Precio por unidad

    private String descripcion; // Descripción del producto, e.g., "Cerveza artesanal"

}
