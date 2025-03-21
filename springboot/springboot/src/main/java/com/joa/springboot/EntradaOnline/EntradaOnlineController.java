package com.joa.springboot.EntradaOnline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/entradaOnline")
public class EntradaOnlineController {

    @Autowired
    private EntradaOnlineService EntradaOnlineService;

    // Crear EntradaOnline
    @PostMapping
    public ResponseEntity<EntradaOnlineResponseDTO> crearEntradaOnline(@Valid @RequestBody EntradaOnlineRequestDTO requestDTO) {
        return ResponseEntity.ok(EntradaOnlineService.crearEntradaOnline(requestDTO));
    }

    // Listar todas las EntradaOnlines
    @GetMapping
    public ResponseEntity<List<EntradaOnlineResponseDTO>> listarEntradaOnline() {
        return ResponseEntity.ok(EntradaOnlineService.listarEntradaOnline());
    }

    // Obtener una EntradaOnline por ID
    @GetMapping("/{id}")
    public ResponseEntity<EntradaOnlineResponseDTO> obtenerEntradaOnlinePorId(@PathVariable Long id) {
        return ResponseEntity.ok(EntradaOnlineService.obtenerEntradaOnlinePorId(id));
    }

    // Actualizar una EntradaOnline
    @PutMapping("/{id}")
    public ResponseEntity<EntradaOnlineResponseDTO> actualizarEntradaOnline(
            @PathVariable Long id,
            @Valid @RequestBody EntradaOnlineRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(EntradaOnlineService.actualizarEntradaOnline(id, requestDTO));
    }

    // Eliminar una EntradaOnline
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntradaOnline(@PathVariable Long id) {
        EntradaOnlineService.eliminarEntradaOnline(id);
        return ResponseEntity.noContent().build();
    }
}
