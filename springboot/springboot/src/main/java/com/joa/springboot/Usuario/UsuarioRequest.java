package com.joa.springboot.Usuario;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    private int id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String country;
    private String dni;
    private LocalDate fechaNacimiento; 
    private Role role; // Un solo rol
}