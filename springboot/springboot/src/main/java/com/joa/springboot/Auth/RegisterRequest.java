package com.joa.springboot.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String email;
    String username;
    String password;
    String firstname;
    String lastname;
    String country; 
}
