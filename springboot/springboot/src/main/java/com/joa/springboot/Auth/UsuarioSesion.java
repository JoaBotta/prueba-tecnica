package com.joa.springboot.Auth;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@CrossOrigin(origins = "http://localhost:4200/")
public class UsuarioSesion {
    private String email;
    private String username;
    private String firstname;
    private String lastname;
    private String country;
    private String dni;
    
}
