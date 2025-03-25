package com.joa.springboot.EntradaOnline;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EntradaOnlineRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;
}
