package com.joa.springboot.Entrada;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EntradaRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

    @NotNull(message = "El bolicheId es obligatorio")
    private Long bolicheId;
    
}
