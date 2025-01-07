package com.joa.springboot.Barra;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BarraRequestDTO {
    private String producto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private String metodoPago;
    private String numeroTransaccion;
}
