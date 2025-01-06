package com.joa.springboot.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/Usuario")
@RequiredArgsConstructor

@CrossOrigin(origins = {"http://localhost:4200"})
public class UsuarioController {
    private final UsuarioService UsuarioService;
    
    @GetMapping(value = "{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable Integer id)
    {
        UsuarioDTO UsuarioDTO = UsuarioService.getUsuario(id);
        if (UsuarioDTO==null)
        {
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UsuarioDTO);
    }

    @PutMapping()
    public ResponseEntity<UsuarioResponse> updateUsuario(@RequestBody UsuarioRequest UsuarioRequest)
    {
        return ResponseEntity.ok(UsuarioService.updateUsuario(UsuarioRequest));
    }
}