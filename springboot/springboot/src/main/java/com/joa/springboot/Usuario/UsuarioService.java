package com.joa.springboot.Usuario;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository; 

    @Transactional
    public UsuarioResponse updateUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRepository.findById(usuarioRequest.getId())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setFirstname(usuarioRequest.getFirstname());
        usuario.setLastname(usuarioRequest.getLastname());
        usuario.setCountry(usuarioRequest.getCountry());
        usuario.setRole(usuarioRequest.getRole()); // Actualizar el rol

        usuarioRepository.save(usuario);

        return UsuarioResponse.builder()
            .id(usuario.getId())
            .email(usuario.getEmail())
            .username(usuario.getUsername())
            .firstname(usuario.getFirstname())
            .lastname(usuario.getLastname())
            .country(usuario.getCountry())
            .dni(usuario.getDni())
            .role(usuario.getRole())
            .message("El usuario se actualizó satisfactoriamente")
            .build();
    }

    public UsuarioDTO getUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElse(null);

        if (usuario != null) {
            return UsuarioDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .firstname(usuario.getFirstname())
                .lastname(usuario.getLastname())
                .country(usuario.getCountry())
                .dni(usuario.getDni())
                .fechaNacimiento(usuario.getFechaNacimiento())
                .role(usuario.getRole())
                .build();
        }
        return null;
    }
}