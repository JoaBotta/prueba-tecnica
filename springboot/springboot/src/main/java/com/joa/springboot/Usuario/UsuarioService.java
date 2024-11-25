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
        // Llamar al método del repositorio para actualizar los datos
        usuarioRepository.updateUsuario(
            usuarioRequest.getId(), 
            usuarioRequest.getFirstname(), 
            usuarioRequest.getLastname(), 
            usuarioRequest.getCountry()
        );

        // Retornar mensaje de éxito
        return new UsuarioResponse("El usuario se actualizó satisfactoriamente");
    }

    public UsuarioDTO getUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
       
        if (usuario != null) {
            return UsuarioDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .firstname(usuario.getFirstname())
                .lastname(usuario.getLastname())
                .country(usuario.getCountry())
                .build();
        }
        return null;
    }
}
