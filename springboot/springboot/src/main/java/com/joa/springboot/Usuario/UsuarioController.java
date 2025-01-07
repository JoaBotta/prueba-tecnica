package com.joa.springboot.Usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/Usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping(value = "{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Integer id) {
        UsuarioDTO usuarioDTO = usuarioService.getUsuario(id);
        if (usuarioDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping()
    public ResponseEntity<UsuarioResponse> updateUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuarioRequest));
    }
}
