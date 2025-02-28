package com.joa.springboot.Auth;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
@NoArgsConstructor
public class LoginRequest {
    String email;
    String password; 
}