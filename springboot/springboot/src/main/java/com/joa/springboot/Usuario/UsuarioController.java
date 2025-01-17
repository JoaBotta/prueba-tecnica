package com.joa.springboot.Usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.Authentication;

@RestController
@RequestMapping(value = "/api/usuario")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.obtenerUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Usuario> getCurrentUser(Authentication authentication) {
        String userEmail = authentication.getName();
        return usuarioService.findByEmail(userEmail)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
