package com.joa.springboot.VentaEntrada;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class VentaEntradaRequestDTO {

    @NotNull
    private Long puntoVentaId;

    @NotNull
    private Long empleadoVentasId;

    @NotNull
    private Long formaDePagoId;

    private Long entradaId;
    private Long qrEntradaId;
    private int cantidad;

    private String nombreComprador;
    private String correoElectronico;
    private String telefono;
}
