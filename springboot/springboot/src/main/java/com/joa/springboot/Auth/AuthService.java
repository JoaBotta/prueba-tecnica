package com.joa.springboot.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.joa.springboot.Usuario.Role;
import com.joa.springboot.Usuario.Usuario;
import com.joa.springboot.Usuario.UsuarioRepository;
import com.joa.springboot.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthService {

    private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Usuario user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + request.getEmail()));

        String token = jwtService.getToken(user);

        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado: " + request.getEmail());
        }

        // Si no se envía rol, asignamos USER por defecto
        Role role = request.getRole() != null ? request.getRole() : Role.USER;

        Usuario usuario = Usuario.builder()
            .email(request.getEmail())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .country(request.getCountry())
            .role(role)
            .build();

        userRepository.save(usuario);

        return AuthResponse.builder()
            .token(jwtService.getToken(usuario))
            .build();
    }
}
