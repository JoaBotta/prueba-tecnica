package com.joa.springboot.Barra;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BarraResponseDTO {
    private Long id;
    private String producto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal total; // Total = precioUnitario * cantidad
    private String metodoPago;
    private String numeroTransaccion;
    private LocalDateTime fechaHora;
}
