package com.joa.springboot.QrEntrada;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class QrEntradaRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

    @NotBlank(message = "El nombre del comprador no puede estar vacío")
    private String nombreComprador;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    private String correoElectronico;

    @NotBlank(message = "El teléfono no puede estar vacío")
    private String telefono;
}
