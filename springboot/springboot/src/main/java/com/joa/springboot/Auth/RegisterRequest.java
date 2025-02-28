package com.joa.springboot.Auth;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class RegisterRequest {
    String email;
    String username;
    String password;
    String firstname;
    String lastname;
    String country; 
}
