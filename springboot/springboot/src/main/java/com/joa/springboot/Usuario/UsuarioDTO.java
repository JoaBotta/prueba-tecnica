package com.joa.springboot.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String country;
    //private String dni; 
    private LocalDate fechaNacimiento; 
    private String password;
    private Role role; // Un solo rol
}