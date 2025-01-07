package com.joa.springboot.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private Integer id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String country;
    private String dni;
    private Role role; // Un solo rol
    private String message; // Mensaje adicional
}