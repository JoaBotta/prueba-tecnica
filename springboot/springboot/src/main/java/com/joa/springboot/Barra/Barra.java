package com.joa.springboot.Barra;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor 
@Entity
@Table(name = "barra")
public class Barra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String producto; // Nombre del producto vendido (e.g., trago específico)

    @Column(nullable = false)
    private Integer cantidad; // Cantidad de productos vendidos

    @Column(nullable = false)
    private BigDecimal precioUnitario; // Precio por unidad

    @Column(nullable = false)
    private String metodoPago; // Efectivo, Tarjeta, QR, etc.

    private String numeroTransaccion; // Número de transacción para métodos digitales (opcional)

    @Column(nullable = false)
    private LocalDateTime fechaHora; // Fecha y hora de la venta
}
