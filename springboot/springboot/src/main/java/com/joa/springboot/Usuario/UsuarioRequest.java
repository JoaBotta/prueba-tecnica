package com.joa.springboot.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {
    int id;
    String email;
    String username;
    String firstname;
    String lastname;
    String country;
}